package id.co.app.application.controller;

import id.co.app.application.service.MasterDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rest/common")
public class CommonController {

    @Autowired
    MasterDataSvc masterDataSvc;

    static List<String> provinsiList = null;

    static {
        provinsiList = new ArrayList<String>();
        provinsiList.add("ACEH");
        provinsiList.add("SUMATERA UTARA");
        provinsiList.add("SUMATERA BARAT");
        provinsiList.add("RIAU");
        provinsiList.add("JAMBI");
        provinsiList.add("SUMATERA SELATAN");
        provinsiList.add("BENGKULU");
        provinsiList.add("LAMPUNG");
        provinsiList.add("KEPULAUAN BANGKA BELITUNG");
        provinsiList.add("KEPULAUAN RIAU");
        provinsiList.add("DKI JAKARTA");
        provinsiList.add("JAWA BARAT");
        provinsiList.add("JAWA TENGAH");
        provinsiList.add("DI YOGYAKARTA");
        provinsiList.add("JAWA TIMUR");
        provinsiList.add("BANTEN");
        provinsiList.add("BALI");
        provinsiList.add("NUSA TENGGARA BARAT");
        provinsiList.add("NUSA TENGGARA TIMUR");
        provinsiList.add("KALIMANTAN BARAT");
        provinsiList.add("KALIMANTAN TENGAH");
        provinsiList.add("KALIMANTAN SELATAN");
        provinsiList.add("KALIMANTAN TIMUR");
        provinsiList.add("SULAWESI UTARA");
        provinsiList.add("SULAWESI SELATAN");
        provinsiList.add("SULAWESI TENGGARA");
        provinsiList.add("GORONTALO");
        provinsiList.add("SULAWESI BARAT");
        provinsiList.add("MALUKU");
        provinsiList.add("MALUKU UTARA");
        provinsiList.add("PAPUA");
        provinsiList.add("PAPUA BARAT");
        provinsiList.add("SULAWESI TENGAH");
        provinsiList.add("KALIMANTAN UTARA");
    }

    @RequestMapping(value = "/listprovinsi", method = RequestMethod.GET)
    public String getListProvinsi(Model model){
        provinsiList = new ArrayList<String>();
        provinsiList.add("ACEH");
        provinsiList.add("SUMATERA UTARA");
        provinsiList.add("SUMATERA BARAT");
        provinsiList.add("RIAU");
        provinsiList.add("JAMBI");
        provinsiList.add("SUMATERA SELATAN");
        provinsiList.add("BENGKULU");
        provinsiList.add("LAMPUNG");
        provinsiList.add("KEPULAUAN BANGKA BELITUNG");
        provinsiList.add("KEPULAUAN RIAU");
        provinsiList.add("DKI JAKARTA");
        provinsiList.add("JAWA BARAT");
        provinsiList.add("JAWA TENGAH");
        provinsiList.add("DI YOGYAKARTA");
        provinsiList.add("JAWA TIMUR");
        provinsiList.add("BANTEN");
        provinsiList.add("BALI");
        provinsiList.add("NUSA TENGGARA BARAT");
        provinsiList.add("NUSA TENGGARA TIMUR");
        provinsiList.add("KALIMANTAN BARAT");
        provinsiList.add("KALIMANTAN TENGAH");
        provinsiList.add("KALIMANTAN SELATAN");
        provinsiList.add("KALIMANTAN TIMUR");
        provinsiList.add("SULAWESI UTARA");
        provinsiList.add("SULAWESI SELATAN");
        provinsiList.add("SULAWESI TENGGARA");
        provinsiList.add("GORONTALO");
        provinsiList.add("SULAWESI BARAT");
        provinsiList.add("MALUKU");
        provinsiList.add("MALUKU UTARA");
        provinsiList.add("PAPUA");
        provinsiList.add("PAPUA BARAT");
        provinsiList.add("SULAWESI TENGAH");
        provinsiList.add("KALIMANTAN UTARA");
        model.addAttribute("provinsiLists", provinsiList);
        return "provinsiList";
    }


}
