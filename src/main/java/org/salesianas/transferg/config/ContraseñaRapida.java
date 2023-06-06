package org.salesianas.transferg.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ContraseñaRapida {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new BCryptPasswordEncoder().encode("123Antoñito;"));

	}

}
