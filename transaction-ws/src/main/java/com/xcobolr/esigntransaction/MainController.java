package com.xcobolr.esigntransaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.xcobolr.esigntransaction.error.GlobalExceptionHandler;
import com.xcobolr.esigntransaction.model.Employee;
import com.xcobolr.esigntransaction.repository.EmployeeRepository;




@RestController // This means that this class is a Controller
@RequestMapping(path="/api/transaction") // This means URL's start with /demo (after Application path)
@Api(value="Employee Management System", description="Operations pertaining to Employee in Employee Management System")
public class MainController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private EmployeeRepository employeeRepository;

  @ApiOperation(value = "View a list of available employees", response = List.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @GetMapping("/all")
  public @ResponseBody Iterable<Employee> getAllEployees() {
    // This returns a JSON or XML with the users
    return employeeRepository.findAll();
  }
  
  
  
  @ApiOperation(value = "Add a Employee")
  @PostMapping(path="/add") // Map ONLY POST Requests
  public Employee addNewEmployee (@ApiParam(value = "Employee object stored in database table", required = true) @Valid @RequestBody Employee employee) {
	    return  employeeRepository.save(employee);
  }



}