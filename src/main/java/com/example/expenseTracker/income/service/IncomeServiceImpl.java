package com.example.expenseTracker.income.service;

import com.example.expenseTracker.budget.model.Budget;
import com.example.expenseTracker.exceptionHandling.exception.UnAuthorizedAccessException;
import com.example.expenseTracker.income.model.Income;
import com.example.expenseTracker.income.repository.IncomeRepository;
import com.example.expenseTracker.user.model.User;
import com.example.expenseTracker.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class IncomeServiceImpl implements IncomeService{

    private final IncomeRepository incomeRepository;
    private final UserService userService;

    @Override
    public List<Income> findAll(Pageable pageable) {
        return incomeRepository.findByUserId(userService.findCurrentUser().getId(), pageable);
    }

    @Override
    public Income findById(int id) {
        return incomeRepository.findByIdAndUserId(id, userService.findCurrentUser().getId());
    }

    @Override
    public Income save(Income request) {
        Income income = Income.builder().
                user(userService.findCurrentUser()).
                source(request.getSource()).
                amount(request.getAmount()).
                description(request.getDescription()).
                date(request.getDate()).
                build();
        return incomeRepository.save(income);
    }

    @Override
    public Income update(Income request) {
        User user = userService.findCurrentUser();
        Optional<Income> dbIncome = incomeRepository.findById(request.getId());
        if(dbIncome.isPresent() && dbIncome.get().getUser().equals(user)) {
            Income income = Income.builder().
                    user(userService.findCurrentUser()).
                    source(request.getSource()).
                    amount(request.getAmount()).
                    description(request.getDescription()).
                    date(request.getDate()).
                    build();
            return incomeRepository.save(income);
        }
        else throw new UnAuthorizedAccessException("Access Denied");
    }

    @Override
    public void delete(int id) {
        User user = userService.findCurrentUser();
        Optional<Income> dbIncome = incomeRepository.findById(id);
        if(dbIncome.isPresent() && dbIncome.get().getUser().equals(user)) {
            incomeRepository.deleteById(id);
        }
        else throw new UnAuthorizedAccessException("Access Denied");
    }
}
