package com.example.carrental.controller;

import com.example.carrental.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tenant")
public class TenantController {
    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping()
    public String getTenantPage(Model model) {
        model.addAttribute("tenants", tenantService.getAll());
        return "Tenant";
    }

    @PostMapping("/add")
    public String addTenant(@RequestParam String phoneNumber,
                            @RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam short yearDriving) {
        tenantService.add(phoneNumber, firstName, lastName, yearDriving);
        return "redirect:/tenant";
    }

    @PostMapping("/delete")
    public String deleteTenantByPhoneNumber(@RequestParam String phoneNumber) {
        tenantService.delete(phoneNumber);
        return "redirect:/tenant";
    }

    @PostMapping("/update")
    public String updateTenantByPhoneNumber(@RequestParam String firstName,
                                            @RequestParam String lastName,
                                            @RequestParam short yearDriving,
                                            @RequestParam String phoneNumber){
        tenantService.update(firstName, lastName, yearDriving, phoneNumber);
        return "redirect:/tenant";
    }
}
