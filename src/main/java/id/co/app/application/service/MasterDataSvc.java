package id.co.app.application.service;

import id.co.app.application.domain.model.AppException;
import id.co.app.application.domain.model.Konstanta;
import id.co.app.application.domain.model.ResponseModel;
import id.co.app.application.domain.model.request.ReqDataResponden;
import id.co.app.application.domain.model.request.ReqGantiPass;
import id.co.app.application.domain.model.request.ReqDataUser;
import id.co.app.application.domain.table.*;
import id.co.app.application.repository.*;
import id.co.app.application.repository.repoModel.ProfilRespondenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class MasterDataSvc {

    @Autowired
    MsUserRepo msUserRepo;

    @Autowired
    MsUserRoleRepo msUserRoleRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    LogSvc logSvc;

    @Autowired
    TbRespondenRepo tbRespondenRepo;

    @Autowired
    TbPenelitiRepo tbPenelitiRepo;

    public ResponseModel getDaftarUser(){
        ResponseModel res = new ResponseModel("Get daftar seluruh PIC");
        List<MsUser> users = msUserRepo.findAll();
        res.setObject(users);
        return res;
    }

    public ResponseModel getDataUser(String idUser){
        ResponseModel res = new ResponseModel("Get data PIC");
        MsUser user = msUserRepo.findByIdMsUser(idUser);
        if(user == null){
            throw new AppException("PIC tidak ditemukan");
        }
        res.setObject(user);
        return res;
    }

    @Transactional
    public ResponseModel simpanPass(String idUser, ReqGantiPass req){
        System.out.println(encoder.encode("tesdulu"));
        ResponseModel res = new ResponseModel("Update data password");
        MsUser user = msUserRepo.findByIdMsUser(idUser);
        if(user == null){
            throw new AppException("User PIC tidak ditemukan");
        }
        if(!encoder.matches(req.getPassOld(), user.getPwd())){
            throw new AppException("Password lama yang dimasukkan salah");
        }
        user.setPwd(encoder.encode(req.getPassNew()));
        msUserRepo.save(user);

        logSvc.simpanLog(idUser, Konstanta.AKSI_UPDATE, Konstanta.TABEL_MS_USER, user.getIdMsUser(), res.getTitle());
        res.setObject(user);
        return res;
    }

    @Transactional
    public ResponseModel resetPass(String idUserReset, String idUser){
        ResponseModel res = new ResponseModel("Reset password pengguna");
        MsUser user = msUserRepo.findByIdMsUser(idUserReset);
        if(user == null){
            throw new AppException("User PIC tidak ditemukan");
        }
        user.setPwd(encoder.encode(user.getIdMsUser()));
        msUserRepo.save(user);

        logSvc.simpanLog(idUser, Konstanta.AKSI_UPDATE, Konstanta.TABEL_MS_USER, user.getIdMsUser(), res.getTitle());
        res.setObject(user);
        return res;
    }

    @Transactional
    public ResponseModel simpanPenggunaBaru(String idUser, ReqDataUser dataUser){
        ResponseModel res = new ResponseModel("Tambah data PIC baru");
        MsUser user = msUserRepo.findByIdMsUser(dataUser.getIdUser());
        if(user != null){
            throw new AppException("Pegawai dengan NIP yang sama sudah pernah direkam");
        }
        user = new MsUser();
        user.setIdMsUser(dataUser.getIdUser());
        user.setNama(dataUser.getNama());
        user.setTelp(dataUser.getNotelp());
        user.setPwd(encoder.encode(dataUser.getIdUser()));
        user.setFgAktif(Konstanta.IS_AKTIF);
        msUserRepo.save(user);

        //default role daftar adalah ROLE_USER
        MsUserRole userRole = new MsUserRole();
        userRole.setIdMsRole("ROLE_USER");
        userRole.setIdMsUser(dataUser.getIdUser());
        msUserRoleRepo.save(userRole);

        logSvc.simpanLog(idUser, Konstanta.AKSI_SIMPAN, Konstanta.TABEL_MS_USER, user.getIdMsUser(), res.getTitle());
        res.setObject(user);
        return res;
    }

    @Transactional
    public ResponseModel simpanRegister(String idUser, ReqDataUser dataUser){
        ResponseModel res = new ResponseModel("Tambah Pengguna Baru");
        MsUser user = msUserRepo.findByIdMsUser(dataUser.getIdUser());
        if(user != null){
            throw new AppException("Username sudah ada yang menggunakan");
        }
        user = new MsUser();
        user.setIdMsUser(dataUser.getIdUser());
        user.setNama(dataUser.getNama());
        user.setTelp(dataUser.getNotelp());
        user.setPwd(encoder.encode(dataUser.getIdUser()));
        user.setRole(dataUser.getRoleUser());
        user.setFgAktif(Konstanta.IS_AKTIF);
        msUserRepo.save(user);

        //default role daftar adalah ROLE_USER
        MsUserRole userRole = new MsUserRole();
        userRole.setIdMsRole(dataUser.getRoleUser());
        userRole.setIdMsUser(dataUser.getIdUser());
        msUserRoleRepo.save(userRole);

        logSvc.simpanLog(idUser, Konstanta.AKSI_SIMPAN, Konstanta.TABEL_MS_USER, user.getIdMsUser(), res.getTitle());
        res.setObject(user);
        return res;
    }

    @Transactional
    public ResponseModel updateDataPic(String idUser, ReqDataUser dataUser){
        ResponseModel res = new ResponseModel("Update data PIC");
        MsUser user = msUserRepo.findByIdMsUser(idUser);
        if(user == null){
            throw new AppException("User PIC tidak ditemukan");
        }
        user.setTelp(dataUser.getNotelp());
        user.setFgAktif(dataUser.getFgAktif());
        msUserRepo.save(user);

        logSvc.simpanLog(idUser, Konstanta.AKSI_UPDATE, Konstanta.TABEL_MS_USER, user.getIdMsUser(), res.getTitle());
        res.setObject(user);
        return res;
    }

    public ResponseModel getDataResponden(String idUser){
        ResponseModel res = new ResponseModel("Get data Responden");
        ProfilRespondenModel responder = tbRespondenRepo.findResponden(idUser);
        if(responder == null){
            throw new AppException("Silahkan dilengkapi data profil");
        }
        res.setObject(responder);
        return res;
    }

    @Transactional
    public ResponseModel updateDataResponden(String idUser, ReqDataResponden dataUser){
        ResponseModel res = new ResponseModel("Update data Responden");
        MsUser user = msUserRepo.findByIdMsUser(idUser);
        if(user == null){
            throw new AppException("Responden tidak ditemukan");
        }
        user.setTelp(dataUser.getNoTelp());
        msUserRepo.save(user);

        //insert ke tabel responden
        TbResponden tbRes = tbRespondenRepo.findByChIdUser(idUser);

        if(tbRes == null){
            TbResponden tbResp = new TbResponden();
            tbResp.setChIdUser(idUser);
            tbResp.setChEmailRes(dataUser.getEmail());
            tbResp.setChGenderRes(dataUser.getJnsKelamin());
            tbResp.setDtTglLahirRes(new Date(dataUser.getTglLahir()));
            tbResp.setChKtpRes(dataUser.getNoIdentitas());
            tbResp.setChPendidikanRes(dataUser.getPendidikan());
            tbResp.setChPekerjaanRes(dataUser.getPekerjaan());
            tbResp.setNumPenghasilan_minRes(dataUser.getGajiMin());
            tbResp.setNumPenghasilan_maxRes(dataUser.getGajiMax());
            tbResp.setNumStsMenikah(dataUser.getStsKawin());
            tbResp.setNumJmlAnak(dataUser.getJmlAnak());
            tbResp.setChAlamatDomisili(dataUser.getAlamatDomisili());
            tbResp.setNumProvDomisili(dataUser.getProvinsiDomisili());
            tbResp.setChAlamatKerja(dataUser.getAlamatKerja());
            tbResp.setNumProvKerja(dataUser.getProvinsiKerja());
            tbResp.setChBankRekening(dataUser.getNamaBank());
            tbResp.setChNomorRekening(dataUser.getNoRekening());
            tbRespondenRepo.save(tbResp);
        }else{
            tbRes.setChIdUser(idUser);
            tbRes.setChEmailRes(dataUser.getEmail());
            tbRes.setChGenderRes(dataUser.getJnsKelamin());
            tbRes.setDtTglLahirRes(new Date(dataUser.getTglLahir()));
            tbRes.setChKtpRes(dataUser.getNoIdentitas());
            tbRes.setChPendidikanRes(dataUser.getPendidikan());
            tbRes.setChPekerjaanRes(dataUser.getPekerjaan());
            tbRes.setNumPenghasilan_minRes(dataUser.getGajiMin());
            tbRes.setNumPenghasilan_maxRes(dataUser.getGajiMax());
            tbRes.setNumStsMenikah(dataUser.getStsKawin());
            tbRes.setNumJmlAnak(dataUser.getJmlAnak());
            tbRes.setChAlamatDomisili(dataUser.getAlamatDomisili());
            tbRes.setNumProvDomisili(dataUser.getProvinsiDomisili());
            tbRes.setChAlamatKerja(dataUser.getAlamatKerja());
            tbRes.setNumProvKerja(dataUser.getProvinsiKerja());
            tbRes.setChBankRekening(dataUser.getNamaBank());
            tbRes.setChNomorRekening(dataUser.getNoRekening());
            tbRespondenRepo.save(tbRes);
        }

        logSvc.simpanLog(idUser, Konstanta.AKSI_UPDATE, Konstanta.TABEL_MS_USER, user.getIdMsUser(), res.getTitle());
        res.setObject(user);
        return res;
    }

    //PENELITI
    public ResponseModel getDataPeneliti(String idUser){
        ResponseModel res = new ResponseModel("Get data Responden");
        ProfilRespondenModel responder = tbPenelitiRepo.findPeneliti(idUser);
        if(responder == null){
            throw new AppException("Silahkan dilengkapi data profil");
        }
        res.setObject(responder);
        return res;
    }


    @Transactional
    public ResponseModel updateDataPeneliti(String idUser, ReqDataResponden dataUser){
        ResponseModel res = new ResponseModel("Update data Responden");
        MsUser user = msUserRepo.findByIdMsUser(idUser);
        if(user == null){
            throw new AppException("Peneliti tidak ditemukan");
        }
        user.setTelp(dataUser.getNoTelp());
        msUserRepo.save(user);

        //insert ke tabel responden
        TbPeneliti tbRes = tbPenelitiRepo.findByChIdUser(idUser);

        if(tbRes == null){
            TbPeneliti tbResp = new TbPeneliti();
            tbResp.setChIdUser(idUser);
            tbResp.setChEmailPeneliti(dataUser.getEmail());
            tbResp.setChPekerjaanPeneliti(dataUser.getPekerjaan());
            tbPenelitiRepo.save(tbResp);
        }else{
            tbRes.setChIdUser(idUser);
            tbRes.setChEmailPeneliti(dataUser.getEmail());
            tbRes.setChPekerjaanPeneliti(dataUser.getPekerjaan());
            tbPenelitiRepo.save(tbRes);
        }

        logSvc.simpanLog(idUser, Konstanta.AKSI_UPDATE, Konstanta.TABEL_MS_USER, user.getIdMsUser(), res.getTitle());
        res.setObject(user);
        return res;
    }

}
