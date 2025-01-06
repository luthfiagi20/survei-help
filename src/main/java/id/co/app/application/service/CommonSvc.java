package id.co.app.application.service;

import id.co.app.application.domain.model.AppException;
import id.co.app.application.domain.model.ImageModel;
import id.co.app.application.domain.model.Konstanta;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommonSvc {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonSvc.class);

    @Value("${app.fileupload.dir}")
    private String homedir;

    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public CommonSvc() {
        super();
    }

    public String timeFormatter() {
        SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
        String time = sdf.format(new Date());

        return time;
    }

    public String getClientIpAddressIfServletRequestExist() {

        if (RequestContextHolder.getRequestAttributes() == null) {
            return "0.0.0.0";
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        for (String header: IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(header);
            if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
                String ip = ipList.split(",")[0];
                return ip;
            }
        }

        return request.getRemoteAddr();
    }

//    public void setActiveMenu(List<Menu> menusTobeActivated, List<UserMenu> userMenus) {
//
//        for (int i = 0; i < userMenus.size(); i++) { //kategori
//            List<UserMenu> headers = userMenus.get(i).getSubMenu();
//            for (int j = 0; j < headers.size(); j++) { //header
//                if (headers.get(j).getActive() == 1) {
//                    headers.get(j).setActive(0);
//                    List<UserMenu> subMenus = headers.get(j).getSubMenu();
//                    for (int k = 0; k < subMenus.size(); k++) {
//                        if (subMenus.get(k).getActive() == 1) {
//                            subMenus.get(k).setActive(0);
//                        }
//                    }
//                }
//            }
//        }
//
//        for (int i = 0; i < userMenus.size(); i++) { //kategori
//            List<UserMenu> headers = userMenus.get(i).getSubMenu();
//            for (int j = 0; j < headers.size(); j++) { //header
//                if (menusTobeActivated.contains(headers.get(j).getMenu())) {
//                    headers.get(j).setActive(1);
//                    List<UserMenu> subMenus = headers.get(j).getSubMenu();
//                    for (int k = 0; k < subMenus.size(); k++) {
//                        if (menusTobeActivated.contains(subMenus.get(k).getMenu())) {
//                            subMenus.get(k).setActive(1);
//                            return;
//                        }
//                    }
//                }
//            }
//        }
//    }

    public String getFormatNpwp(String npwp) {
        if (npwp == null) {
            return "00.000.000.0-000.000";
        }

        npwp = npwp.substring(0, 2) + "." + npwp.substring(2, 5) + "."
                + npwp.substring(5, 8) + "." + npwp.charAt(8) + "-"
                + npwp.substring(9, 12) + "." + npwp.substring(12, 15);

        return npwp;
    }

    public String getFormatNip(String nip) {
        nip = nip.substring(0, 8) + " " + nip.substring(8, 14) + " "
                + nip.charAt(14) + " " + nip.substring(15, 18);

        return nip;
    }

    public String capsFirst(String str) {
        List<String> romawi = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
        String[] words = str.split(" ");
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String[] wordsInnerDot = words[i].split("\\.");
            if (wordsInnerDot.length > 1) {
                for (int j = 0; j < wordsInnerDot.length; j++) {
                    if (romawi.contains(wordsInnerDot[j])) {
                        ret.append(wordsInnerDot[j]);
                    } else {
                        ret.append(Character.toUpperCase(wordsInnerDot[j].charAt(0)));
                        ret.append(wordsInnerDot[j].substring(1).toLowerCase());
                    }

                    if (j < wordsInnerDot.length - 1) {
                        ret.append('.');
                    }
                }
            } else {
                if (romawi.contains(words[i])) {
                    ret.append(words[i]);
                } else {
                    ret.append(Character.toUpperCase(words[i].charAt(0)));
                    ret.append(words[i].substring(1).toLowerCase());
                }
            }
            if (i < words.length - 1) {
                ret.append(' ');
            }
        }
        return ret.toString();
    }

    public String SHAVal(String inputVal) throws Exception {
        inputVal = Konstanta.SALT + inputVal;
        MessageDigest myDigest = MessageDigest.getInstance("SHA-256");
        myDigest.update(inputVal.getBytes());
        byte[] dataBytes = myDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < dataBytes.length; i++) {
            sb.append(Integer.toString((dataBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public Date stringDdmmyyyTodate(String ddmmyyy, String namafield, boolean nullable) throws AppException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date res = null;

        if (ddmmyyy == null || ddmmyyy.equals("")) {
            if (nullable) {
                return res;
            } else {
                throw new AppException("Parsing error : " + namafield, null);
            }
        }

        try {
            res = sdf.parse(ddmmyyy);
        } catch (ParseException e) {
            throw new AppException("Parsing error : " + namafield + " : " + ddmmyyy, null);
        }

        return res;
    }

    public String dateToIndonesianDate(Date ddmmyyy, String namafield, boolean nullable) throws AppException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String res = "";

        if (ddmmyyy == null) {
            if (nullable) {
                return res;
            } else {
                throw new AppException("Parsing error : " + namafield, null);
            }
        }
        res = sdf.format(ddmmyyy);
        String[] temp = res.split("-");
        temp[1] = Konstanta.BULAN[Integer.parseInt(temp[1])];
        res = temp[0] + " " + temp[1] + " " + temp[2];

        return res;
    }

    public String ddmmyyyyToIndonesianDate(String ddmmyyy, String namafield) throws AppException {
        String res = "-";
        if (ddmmyyy == null || ddmmyyy.equals("")) {
            return res;
        }

        String[] temp = ddmmyyy.split("-");
        if (temp.length < 3) {
            throw new AppException("Parsing error : " + namafield, null);
        }
        temp[1] = Konstanta.BULAN[Integer.parseInt(temp[1])];
        res = temp[0] + " " + temp[1] + " " + temp[2];

        return res;
    }

    public String dateToStringDdmmyyy(Date ddmmyyy, String namafield, boolean nullable) throws AppException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String res = "";

        if (ddmmyyy == null) {
            if (nullable) {
                return res;
            } else {
                throw new AppException("Parsing error : " + namafield, null);
            }
        }
        res = sdf.format(ddmmyyy);

        return res;
    }

//    public void converPdf(String workingDir, String filename) {
//        workingDir = workingDir.replace('\\', '/');
//        // Initialise
//        File inputFileTxt = new File(workingDir, filename + ".docx");
//        File outputFile = new File(workingDir, filename + ".pdf");
//        try {
//            converter.convert(inputFileTxt).to(outputFile).execute();
//        } catch (OfficeException e) {
//            LOGGER.error("Error convert PDF", e);
//            e.printStackTrace();
//        }
//    }

//    public byte[] convertPdf(ByteArrayOutputStream docxOutputStream){
//        InputStream docxInputStream = new ByteArrayInputStream(docxOutputStream.toByteArray());
//        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
//        try {
//            converter.convert(docxInputStream).as(DefaultDocumentFormatRegistry.DOCX).
//                    to(pdfOutputStream).as(DefaultDocumentFormatRegistry.PDF).execute();
//        } catch (OfficeException e) {
//            LOGGER.error("Error convert PDF", e);
//            e.printStackTrace();
//        }
//
//        return pdfOutputStream.toByteArray();
//    }

    public Long StringToLong(String id){
        Long res;
        try{
            res = Long.valueOf(id);
        }catch (Exception e){
            throw new AppException("Id Data tidak valid", e);
        }
        return res;
    }

    public Integer StringToInteger(String id){
        Integer res;
        try{
            res = Integer.valueOf(id);
        }catch (Exception e){
            throw new AppException("Id Data tidak valid", e);
        }
        return res;
    }


    public void createUserDirectories(Long idMsUser) {
        File d = new File(homedir + File.separator + "user-" + idMsUser);
        if (!d.exists()) {
            d.mkdirs();
        }
    }

    public String generateRandomNumber(int jmlDigit) {
        Character[] numericCoollection = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Random ran = new Random(new Date().getTime());
        char[] randomChar = new char[jmlDigit];
        for (int i = 0; i < randomChar.length; i++) {
            randomChar[i] = numericCoollection[ran.nextInt(numericCoollection.length)];
        }
        return new String(randomChar);
    }

    public String encrypt(String strClearText) {
        String pre = generateRandomNumber(4);
        String post = generateRandomNumber(7);
        String strData = "";

        if (strClearText == null || strClearText.equals("")) {
            return "";
        }

        strClearText = pre + strClearText + post; //xxxxidxxxxxxx

        byte[] encodedBytes = Base64.getEncoder().encode(strClearText.getBytes());
        strData = new String(encodedBytes);

        return strData;
    }

    public String decrypt(String strEncrypted) {
        String strData = "";

        if (strEncrypted == null || strEncrypted.equals("")) {
            return "";
        }

        byte[] decodedBytes = Base64.getDecoder().decode(strEncrypted);
        strData = new String(decodedBytes);

        strData = strData.substring(4);
        strData = strData.substring(0, strData.length() - 7);

        return strData;
    }

    public String formatUangRp(String value) {
        String result = "";
        BigDecimal nilai = null;
        try {
            nilai = new BigDecimal(value);
        } catch (java.lang.Exception e) {
            return result = "0";
        }
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(Locale.GERMANY);
        result = decimalFormat.format(nilai);
        if (result.charAt(0) == '-') {
            result = result.substring(1);
            result = "(" + result + ")";
        }

        return result;
    }

    public void cleanTempFile(String pathTemporary) {
        try {
            File f = new File(pathTemporary);
            FileUtils.cleanDirectory(f); //clean out directory (this is optional -- but good know)
            FileUtils.forceDelete(f); //delete directory
            FileUtils.forceMkdir(f); //create directory
        } catch (IOException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    public ImageModel getMetaFromBase64Image(String imgStr){
        if(imgStr == null || imgStr.equals("")){
            return null;
        }
        ImageModel imageModel = new ImageModel();
        String[] temp = imgStr.split(",");
        if(temp.length != 2){
            throw new AppException("Upload Foto Error, File tidak ditemukan", null);
        }
        temp[0] = temp[0].replace("data:", "");
        temp[0] = temp[0].replace(";base64", "");
        String[] temp2 = temp[0].split("/");
        if(temp.length != 2){
            throw new AppException("Upload Foto Error, File extention tidak ditemukan", null);
        }
        imageModel.setBase64(temp[1]);
        imageModel.setContentType(temp[0]);
        imageModel.setFileExt(temp2[1]);
        return imageModel;
    }

    public String numberToString(Long angka) {
        String[] bil = {"", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas"};
        String x = " ";

        if (angka < 12) {
            x = " " + bil[angka.intValue()];
        } else if (angka < 20) {
            x = numberToString(angka - 10) + " Belas";
        } else if (angka < 100) {
            x = numberToString(angka / 10) + " Puluh" + numberToString(angka % 10);
        } else if (angka < 200) {
            x = " Seratus" + numberToString(angka - 100);
        } else if (angka < 1000) {
            x = numberToString(angka / 100) + " Ratus" + numberToString(angka % 100);
        } else if (angka < 2000) {
            x = " Seribu" + numberToString(angka - 1000);
        } else if (angka < 1000000) {
            x = numberToString(angka / 1000) + " Ribu" + numberToString(angka % 1000);
        } else if (angka < 1000000000) {
            x = numberToString(angka / 1000000) + " Juta" + numberToString(angka % 1000000);
        } else if (angka < 1000000000000l) {
            x = numberToString(angka / 1000000000) + " Miliar" + numberToString(angka % 1000000000);
        } else if (angka < 1000000000000000l) {
            x = numberToString(angka / 1000000000000l) + " Triliun" + numberToString(angka % 1000000000000l);
        }

        return x;
    }

    public String getTampilanUserSidebar(String nama){
        int max = 16;
        String[] temp = nama.split(" ");
        if(temp.length > 1){
            int nama2KataLength = temp[0].length() + 1 + temp[1].length();
            if(nama2KataLength > max){
                String namaAwal = Arrays.stream(Konstanta.NAMA_MUHAMMAD).anyMatch(temp[0]::equals) ? temp[0].charAt(0) + "." : temp[0];
                String namaAkhir = Arrays.stream(Konstanta.NAMA_MUHAMMAD).anyMatch(temp[1]::equals) ? temp[1].charAt(0) + "." : temp[1];
                return namaAwal + " " + namaAkhir;
            }else{
                return temp[0] + " " + temp[1];
            }
        }else{
            return nama;
        }
    }
    public List<String> getLogedUserRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> listAuth = (List<GrantedAuthority>) authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for(GrantedAuthority a : listAuth){
            roles.add(a.getAuthority());
        }
        return roles;
    }

    public String getTimeNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

}

