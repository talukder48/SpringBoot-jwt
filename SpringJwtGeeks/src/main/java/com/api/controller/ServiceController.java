package com.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.MasterBranch;
import com.api.repository.*; 
@RestController
@RequestMapping("/GetBranchListInfo")
public class ServiceController {
	@Autowired
	 MasterBranchRepo MasterBranchRepo;
	
	@PostMapping
	public ResponseEntity<MasterBranch> create(@RequestBody @Valid MasterBranch branch) {
		MasterBranch SaveBranch = MasterBranchRepo.save(branch);
		URI BranchURI = URI.create("/GetBranchListInfo/" + SaveBranch.getBranchCode());
		return ResponseEntity.created(BranchURI).body(SaveBranch);
	}
	
	@GetMapping
	public List<MasterBranch> list() {
		return MasterBranchRepo.findAll();
	}
}
