package sk.ahitka.vetshop.controller.dto;

import lombok.Data;
import lombok.AllArgsConstructor;


@Data
@AllArgsConstructor
public class AuthenticationResponseDto {
    private final String jwt;
}
