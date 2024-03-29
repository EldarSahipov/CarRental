package com.example.carrental.controller;

import com.example.carrental.entity.Tenant;
import com.example.carrental.service.TenantService;
import com.example.carrental.service.UserService;
import com.example.carrental.springsecurity.model.Role;
import com.example.carrental.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Validated
@Controller
@RequestMapping("/tenant")
public class TenantController {
    private final TenantService tenantService;
    private final UserService userService;

    @Autowired
    public TenantController(TenantService tenantService, UserService userService) {
        this.tenantService = tenantService;
        this.userService = userService;
    }

    @GetMapping()
    public String getTenantPage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        verificationUser(currentUser, model);
        model.addAttribute("tenants", tenantService.getAll());
        return "Tenant";
    }

    @PostMapping("/add")
    public String addTenant(@AuthenticationPrincipal UserDetails currentUser,
                            @RequestParam(required = false) String phoneNumber,
                            @RequestParam(required = false) String firstName,
                            @RequestParam(required = false) String lastName,
                            @RequestParam(required = false) Short yearDriving, Model model) {
        verificationUser(currentUser, model);
        if(yearDriving == null) {
            yearDriving = 0;
        }
        if(phoneNumber == null || firstName == null || lastName == null || yearDriving > 88 || yearDriving < 0) {
            model.addAttribute("notAddedTenant", true);
            return "Tenant";
        }
        String regexName = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
        String regexPhoneNumber = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        Pattern patternName = Pattern.compile(regexName);
        Pattern patternPhoneNumber = Pattern.compile(regexPhoneNumber);
        List<String> strings = List.of(firstName, lastName);
        for (String str : strings) {
            Matcher matcher = patternName.matcher(str);
            if (!matcher.matches()) {
                model.addAttribute("notAddedTenant", true);
                model.addAttribute("tenants", tenantService.getAll());
                return "Tenant";
            }
        }
        Matcher matcherPhone = patternPhoneNumber.matcher(phoneNumber);
        if (!matcherPhone.matches()) {
            model.addAttribute("notAddedTenant", true);
            model.addAttribute("tenants", tenantService.getAll());
            return "Tenant";
        }
        Tenant tenant = tenantService.getTenantByPhoneNumber(phoneNumber);
        if(!(tenant == null)) {
            model.addAttribute("numberAlready", true);
            model.addAttribute("tenants", tenantService.getAll());
            return "Tenant";
        }
        tenantService.add(phoneNumber, firstName, lastName, yearDriving);
        model.addAttribute("addedTenant", true);
        return "Tenant";
    }

    @PostMapping("/delete")
    public String deleteTenantByPhoneNumber(@AuthenticationPrincipal UserDetails currentUser,
                                            @RequestParam String phoneNumber, Model model) {
        verificationUser(currentUser, model);
        tenantService.delete(phoneNumber);
        return "Tenant";
    }

    @PostMapping("/update")
    public String updateTenantByPhoneNumber(@AuthenticationPrincipal UserDetails currentUser,
                                            @RequestParam(required = false) String phoneNumber,
                                            @RequestParam(required = false) String firstName,
                                            @RequestParam(required = false) String lastName,
                                            @RequestParam(required = false) Short yearDriving, Model model){
        verificationUser(currentUser, model);
        if(yearDriving == null) {
            yearDriving = 0;
        }
        if(phoneNumber == null || firstName == null || lastName == null || yearDriving > 88 || yearDriving < 0) {
            model.addAttribute("notAddedTenant", true);
            return "Tenant";
        }
        String regexName = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
        String regexPhoneNumber = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        Pattern patternName = Pattern.compile(regexName);
        Pattern patternPhoneNumber = Pattern.compile(regexPhoneNumber);
        List<String> strings = List.of(firstName, lastName);
        for (String str : strings) {
            Matcher matcher = patternName.matcher(str);
            if (!matcher.matches()) {
                model.addAttribute("notUpdateTenant", true);
                model.addAttribute("tenants", tenantService.getAll());
                return "Tenant";
            }
        }
        Matcher matcherPhone = patternPhoneNumber.matcher(phoneNumber);
        if (!matcherPhone.matches()) {
            model.addAttribute("notUpdateTenant", true);
            model.addAttribute("tenants", tenantService.getAll());
            return "Tenant";
        }
        tenantService.update(firstName, lastName, yearDriving, phoneNumber);
        model.addAttribute("updateTenant", true);
        return "Tenant";
    }

    private void verificationUser(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findUserByEmail(currentUser.getUsername()).orElse(null);
        if (user != null && user.getRole() == Role.ADMIN) {
            model.addAttribute("user", "ADMIN");
        } else {
            model.addAttribute("user", "USER");
        }
    }
}
