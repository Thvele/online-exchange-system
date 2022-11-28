package com.funpaycopy.oes.Controller;

import com.funpaycopy.oes.Model.RequestMRG;
import com.funpaycopy.oes.Model.RequestTS;
import com.funpaycopy.oes.Service.Request.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) { this.requestService = requestService; }

    @GetMapping("/requestTS")
    public List<RequestTS> getAllTSRequests() { return requestService.getAllTS(); }

    @GetMapping("/requestMRG")
    public  List<RequestMRG> getAllMRGRequests() { return requestService.getAllMRG(); }

    @PostMapping("/requestTS")
    public RequestTS saveTSRequest(@RequestBody RequestTS requestTS) { return requestService.saveTS(requestTS); }

    @PostMapping("/requestMRG")
    public RequestMRG saveMRGRequest(@RequestBody RequestMRG requestMRG) { return requestService.saveMRG(requestMRG); }

    @GetMapping("/requestTS/{id}")
    public ResponseEntity<RequestTS> getTSByID(@PathVariable("id") Long id) {

        RequestTS requestTS = requestService.getTSByID(id);
        return ResponseEntity.ok(requestTS);
    }

    @GetMapping("/requestMRG/{id}")
    public ResponseEntity<RequestMRG> getMRGByID(@PathVariable("id") Long id) {

        RequestMRG requestMRG = requestService.getMRGByID(id);
        return ResponseEntity.ok(requestMRG);
    }

    @DeleteMapping("/requestTS/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTS(@PathVariable("id") Long id) {

        boolean deleted = requestService.deleteTS(id);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", deleted);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/requestMRG/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMRG(@PathVariable("id") Long id) {

        boolean deleted = requestService.deleteMRG(id);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", deleted);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/requestTS/{id}")
    public ResponseEntity<RequestTS> updateTS(@PathVariable("id") Long id,
                                              @RequestBody RequestTS requestTS) {

        requestTS = requestService.updateTS(id, requestTS);
        return ResponseEntity.ok(requestTS);
    }

    @PutMapping("/requestMRG/{id}")
    public ResponseEntity<RequestMRG> updateTS(@PathVariable("id") Long id,
                                              @RequestBody RequestMRG requestMRG) {

        requestMRG = requestService.updateMRG(id, requestMRG);
        return ResponseEntity.ok(requestMRG);
    }
}