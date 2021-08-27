package de.gagan.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	@GetMapping("/person/V1")
	public PersonV1 getPersonV1() {
		return new PersonV1("Gagan Grover");
	}
	
	@GetMapping("/person/V2")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Priya","Grover"));
	}
	
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 getPersonParamV1() {
		return new PersonV1("Gagan Grover");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 getPersonParamV2() {
		return new PersonV2(new Name("Priya","Grover"));
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 getPersonHeaderV1() {
		return new PersonV1("Gagan Grover");
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 getPersonHeaderV2() {
		return new PersonV2(new Name("Priya","Grover"));
	}
	
	@GetMapping(value="/person/produces", produces="application/de.gagan.rest-v1+json")
	public PersonV1 getPersonProducesV1() {
		return new PersonV1("Gagan Grover");
	}
	
	@GetMapping(value="/person/produces", produces="application/de.gagan.rest-v2+json")
	public PersonV2 getPersonProducesV2() {
		return new PersonV2(new Name("Priya","Grover"));
	}
}
