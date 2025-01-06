package id.co.app.application.service;

import id.co.app.application.domain.table.DatLog;
import id.co.app.application.repository.DatLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class LogSvc {

    @Autowired
    DatLogRepo datLogRepo;

    @Autowired
    CommonSvc commonSvc;

    void simpanLog(String idUser, String aksi, String tabel, String idData, String ket){
        String ip = commonSvc.getClientIpAddressIfServletRequestExist();
        DatLog log = new DatLog();
        log.setIdMsUser(idUser);
        log.setIp(ip);
        log.setAksi(aksi);
        log.setTableTerdampak(tabel);
        log.setIdData(idData);
        log.setTimestamp(new Date());
        log.setKet(ket);
        datLogRepo.save(log);
    }

}
