package api.currency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    @Autowired
    CurrencyRepo currencyRepo;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/")
    public ResponseEntity<String> getCurrencies() {
        try {
            List<Currency> currencies = currencyRepo.findAll();
            return new ResponseEntity<>(objectMapper.writeValueAsString(currencies), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCurrency(@PathVariable("id") Integer id) {
        try {
            Currency currency = currencyRepo.getReferenceById(id);
            return new ResponseEntity<>(objectMapper.writeValueAsString(currency), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> addCurrency(Currency currency) {
        try {
            currency.setUpdateTime(new Date());
            currencyRepo.save(currency);
            return new ResponseEntity<>(objectMapper.writeValueAsString(currency), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/")
    public ResponseEntity<String> updateCurrency(Currency currency) {
        try {
            currency.setUpdateTime(new Date());
            currencyRepo.save(currency);
            return new ResponseEntity<>(objectMapper.writeValueAsString(currency), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateCurrency(@PathVariable("id") Integer id) {
        try {
            currencyRepo.deleteById(id);
            return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
