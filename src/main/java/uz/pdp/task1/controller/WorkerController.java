package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Worker;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.WorkerDto;
import uz.pdp.task1.sevice.WorkerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping
    public ResponseEntity<List<Worker>> getWorker() {
        return ResponseEntity.status(HttpStatus.OK).body(workerService.getWorker());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable int id) {
        Worker worker = workerService.getWorkerById(id);
        return ResponseEntity.status(worker == null ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(worker);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addWorker(@Valid @RequestBody WorkerDto workerDto) {
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editWorker(@PathVariable int id, @Valid @RequestBody WorkerDto workerDto) {
        ApiResponse apiResponse =
                workerService.editWorker(id, workerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWorker(@PathVariable int id) {
        ApiResponse apiResponse = workerService.deleteWorker(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}