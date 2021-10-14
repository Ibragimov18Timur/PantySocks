package com.example.pantrysocks;

import com.example.pantrysocks.socks.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
class Controllers {
    @Autowired
    SocksRepository repository;


    @PostMapping("/api/socks/income")
    public void income(@RequestBody Socks socks) {
        String key = socks.getColor() + socks.getCottonPart();
        Socks oldSocks = repository.getById(key);
        if (oldSocks == null) {
            socks.setId(key);
            repository.save(socks);
        } else {
            int quantity = socks.getQuantity() + oldSocks.getQuantity();
            oldSocks.setQuantity(quantity);
            repository.save(oldSocks);
        }


    }

    @PostMapping("/api/socks/outcome")
    public void outcome(@RequestBody Socks socks) {
        String key = socks.getColor() + socks.getCottonPart();
        Socks oldSocks = repository.getById(key);
        if (oldSocks == null) {
            socks.setId(key);
            socks.setQuantity(-socks.getQuantity());
            repository.save(socks);
        } else {
            int quantity = oldSocks.getQuantity() - socks.getQuantity();
            oldSocks.setQuantity(quantity);
            repository.save(oldSocks);
        }
    }

    @GetMapping("/api/socks")
    public List<Socks> socks(@RequestParam String color, @RequestParam String operation,
                             @RequestParam int cottonPart) {
        return
        repository.findAll().stream().filter(socks -> socks.getColor().equals(color)).
                filter(socks -> {
                            if (operation.equals("moreThan")) {
                                return socks.getCottonPart() > cottonPart;
                            }
                            if (operation.equals("lessThan")) {
                                return socks.getCottonPart() < cottonPart;
                            }
                            return socks.getCottonPart() == cottonPart;

                        }

                ).collect(Collectors.toList());
    }
}