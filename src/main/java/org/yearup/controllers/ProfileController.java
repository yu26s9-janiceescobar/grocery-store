package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_USER')")
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService){
        this.profileService = profileService;
        this.userService = userService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Profile getProfile(Principal principal){
        String username = principal.getName();
        return profileService.getProfile(userService.getIdByUsername(username));
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Profile updateProfile(@RequestBody Profile profile, Principal principal){
        String username = principal.getName();
        return profileService.updateProfile(profile, userService.getIdByUsername(username));
    }

}
