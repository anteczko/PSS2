package com.example.demo.service;

import com.example.demo.model.Delegation;
import com.example.demo.model.User;
import com.example.demo.repository.DelegationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DelegationService {
    @Autowired
    public DelegationService(DelegationRepository delegationRepository) {
        this.delegationRepository = delegationRepository;
    }
    DelegationRepository delegationRepository;
    public Delegation addDelegation(Delegation delegation){ return delegationRepository.save(delegation); }

    public List<Delegation> getAllDelegations(){return delegationRepository.findAll();}

    public List<Delegation> getAllDelegationsOrderByDateStartDesc(){return delegationRepository.findAll().stream().sorted(Comparator.comparing(Delegation::getDateTimeStart)).collect(Collectors.toList());}

    public List<Delegation> getAllDelegationsByUserOrderByDateStartDesc(int userId){
        return delegationRepository.findAll().stream().filter(a->a.getUser().getUserId()==userId).collect(Collectors.toList());
    }

    public Optional<Delegation> getDelegationById(int delegationId){return delegationRepository.findById(delegationId);}

    public boolean deleteDelegation(int delegationId){
        Optional<Delegation> Odelegation = delegationRepository.findById(delegationId);

        if(Odelegation.isPresent()){
            delegationRepository.delete(Odelegation.get());
            return true;
        }
        return false;
    }

}
