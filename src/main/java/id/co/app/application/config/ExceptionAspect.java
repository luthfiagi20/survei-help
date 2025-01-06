package id.co.app.application.config;

import id.co.app.application.domain.model.AppException;
import id.co.app.application.domain.model.ResponseModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@Aspect
public class ExceptionAspect {
	public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAspect.class);

	@Value("${app.prod}")
	private boolean isProduction;

	@Around("execution( id.co.app.application.domain.model.ResponseModel id.co.app.application.controller.*.*(..) )")
	public ResponseModel checkController(ProceedingJoinPoint joinPoint) {

		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();

		ResponseModel result = null;
		try {
			result = (ResponseModel) joinPoint.proceed();
		} catch (Throwable  ex) {
			LOGGER.error("ERROR : " + joinPoint.getSignature().getDeclaringTypeName() + "."
					+ joinPoint.getSignature().getName() + " - " + ex.getMessage() );
			result = new ResponseModel("Oops.. ada error nih!");
			result.setCode("0");
			StringWriter errors = new StringWriter();
			if(ex instanceof AppException){
				if(((AppException) ex).getCarriedException() != null) {
					((AppException) ex).getCarriedException().printStackTrace(new PrintWriter(errors));
					if(!isProduction){
						ex.printStackTrace();
					}
				}
				result.setMsg(ex.getMessage());
			}else if(ex instanceof AccessDeniedException){
				result.setMsg("Maaf anda tidak memiliki hak akses.");
			}else{
				if(!isProduction){
					ex.printStackTrace();
				}
				ex.printStackTrace(new PrintWriter(errors));
				result.setMsg("Silahkan hubungi admin");
			}
			result.setErrorTrace(errors.toString());

		}
		
		return result;
	}

}
