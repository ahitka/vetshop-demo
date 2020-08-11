package sk.ahitka.vetshop.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import sk.ahitka.vetshop.controller.dto.AuthenticationRequestDto;
import sk.ahitka.vetshop.controller.dto.AuthenticationResponseDto;
import sk.ahitka.vetshop.controller.dto.UserDto;
import sk.ahitka.vetshop.service.UserService;
import sk.ahitka.vetshop.util.JtwTokenUtil;
import sk.ahitka.vetshop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JtwTokenUtil jtwTokenUtil;

    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("User disabled", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrent username or password", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequestDto.getUsername());
        final String jwt = jtwTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }


    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.saveUser(user.getUsername(), user.getPassword(), user.getEmail()));
    }
}
