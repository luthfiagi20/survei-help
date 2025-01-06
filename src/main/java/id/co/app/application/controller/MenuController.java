package id.co.app.application.controller;

//import id.co.app.application.service.ProfileSvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {

//    @Autowired
//    ProfileSvc profileSvc;


    @GetMapping("/")
    public String landing(Model model) {

        return "redirect:todolist";
    }

    @GetMapping("/todolist")
    public String welcome(Model model) {
        model.addAttribute("pageTitle","Daftar Pekerjaan");
        return "todolist";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("pageTitle","Beranda");
        return "Home";
    }

    @GetMapping("/admin/user")
    public String adminPic(Model model) {
        model.addAttribute("pageTitle","Administrasi User");
        return "admin/page-user";
    }

    @GetMapping("/profil/akun")
    public String myprofileedit(Model model) {
        model.addAttribute("pageTitle","Ganti Password");
        return "/profile/page-akun";
    }

    @GetMapping("/register")
    public String registrasi(Model model) {
        model.addAttribute("pageTitle","Halaman Registrasi Pengguna");
        return "/register";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        String msg = "";
        if(error != null) {
            msg = "";
            if (error.equalsIgnoreCase("1")) {
                msg = "Mohon tunggu beberapa saat lagi";
            } else if (error.equalsIgnoreCase("2")) {
                msg = "Verifikasi error.";
            } else if (error.equalsIgnoreCase("3")) {
                msg = "Username atau Password salah.";
            }
        }
        model.addAttribute("message",msg);

        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

    //RESPONDEN
    @GetMapping("/responden/profil-responden")
    public String profilResponden(Model model) {
        model.addAttribute("pageTitle","Profil Responden");
        return "/responden/profil-responden";
    }

    @GetMapping("/responden/daftar-survey")
    public String daftarSurvey(Model model) {
        model.addAttribute("pageTitle","Daftar Survey");
        return "/responden/daftar-survey";
    }

    @GetMapping("/responden/riwayat-survey")
    public String riwayatSurvey(Model model) {
        model.addAttribute("pageTitle","Riwayat Survey");
        return "/responden/riwayat-survey";
    }

    @GetMapping("/responden/pencairan-reward")
    public String pencairanReward(Model model) {
        model.addAttribute("pageTitle","Pencairan Reward");
        return "/responden/pencairan-reward";
    }

    //PENELITI
    @GetMapping("/peneliti/profil-peneliti")
    public String profilPeneliti(Model model) {
        model.addAttribute("pageTitle","Profil Peneliti");
        return "/peneliti/profil-peneliti";
    }

    @GetMapping("/peneliti/buat-survey")
    public String buatSurvey(Model model) {
        model.addAttribute("pageTitle","Buat Survey");
        return "/peneliti/buat-survey";
    }

    //CUSTOMER RELATION
    @GetMapping("/customer-relation/pembayaran-survey")
    public String pembayaranSurvey(Model model) {
        model.addAttribute("pageTitle","Daftar Pembayaran Survey");
        return "/customer-relation/pembayaran-survey";
    }

    @GetMapping("/customer-relation/reward-responden")
    public String rewardResponden(Model model) {
        model.addAttribute("pageTitle","Daftar Permohonan Pencairan Reward");
        return "/customer-relation/reward-responden";
    }

    @GetMapping("/customer-relation/persetujuan-reward")
    public String persetujuanReward(Model model) {
        model.addAttribute("pageTitle","Daftar Permintaan Pencairan Reward Responden");
        return "/customer-relation/persetujuan-reward";
    }
}
