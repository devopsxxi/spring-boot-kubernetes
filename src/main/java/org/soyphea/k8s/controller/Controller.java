package org.soyphea.k8s.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soyphea.k8s.config.UserConfig;
import org.soyphea.k8s.domain.User;
import org.soyphea.k8s.srevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.crypto.Cipher;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;

@Slf4j
@RestController
public class Controller {

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private UserService userService;

    public Controller(){
        log.info("Initialised Bean.");
    }
    @GetMapping("/k8s/{name}")
    public String k8sGreeting(@PathVariable("name") String name) {

        log.info("Got the request with name:{}", name);
        return String.format("Hi %s- I am ConfigMap running in side k8s with value %s", name,userConfig);
    }

    @GetMapping("/users/{contain_name}")
    public List<User> getUsersByContainName(@PathVariable("contain_name") String containName){
        return userService.getUser(containName);
    }

   private void test() {
        try {
            Cipher c1 = Cipher.getInstance("DES"); // Noncompliant: DES works with 56-bit keys allow attacks via exhaustive search
            Cipher c7 = Cipher.getInstance("DESede"); // Noncompliant: Triple DES is vulnerable to meet-in-the-middle attack
            Cipher c13 = Cipher.getInstance("RC2"); // Noncompliant: RC2 is vulnerable to a related-key attack
            Cipher c19 = Cipher.getInstance("RC4"); // Noncompliant: vulnerable to several attacks (see https://en.wikipedia.org/wiki/RC4#Security)
            Cipher c25 = Cipher.getInstance("Blowfish"); // Noncompliant: Blowfish use a 64-bit block size makes it vulnerable to birthday attacks

            NullCipher nc = new NullCipher(); // Noncompliant: the NullCipher class provides an "identity cipher" one that does not transform or encrypt the plaintext in any way.
        }
        catch(NoSuchAlgorithmException|NoSuchPaddingException e) {
        }
    }

}
