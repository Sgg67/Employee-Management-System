package employe.manage.ems.controller;

import employe.manage.ems.dto.EmployeeDto;
import employe.manage.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    // Add Employee REST API
    // Post URL: http://localhost:8080/api/employees
    // because it is a Post request it requires a request body
    /* Example Request body
        {
        "firstName":"Sage",
        "lastName": "Yanoff",
        "email": "sageyanoff@gmail.com"
        }
     */

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Get Employee REST API
    // EX: Get URL: http://localhost:8080/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    // Get URL // EX: Get URL: http://localhost:8080/api/employees
    @GetMapping
    // Get All Employees REST API
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}")
    // Update Employee Rest API
    // EX: Put URL: http://localhost:8080/api/employees/1
    // add updated fields for the employee in the body to change employee details
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee){
       EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
       return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("{id}")
    // delete employee Rest API
    // EX: Delete URL: http://localhost:8080/api/employees/1
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Successfully deleted!");
    }
}
