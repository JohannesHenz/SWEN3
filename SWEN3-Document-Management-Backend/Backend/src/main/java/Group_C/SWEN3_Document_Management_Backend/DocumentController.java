package Group_C.SWEN3_Document_Management_Backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class DocumentController {
    @GetMapping("/")
    public String getHello() {
        return "Hello, this is your Document Management System.";
    }
}

