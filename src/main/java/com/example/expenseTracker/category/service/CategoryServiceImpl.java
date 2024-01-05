package com.example.expenseTracker.category.service;

import com.example.expenseTracker.category.model.Category;
import com.example.expenseTracker.category.repository.CategoryRepository;
import com.example.expenseTracker.exceptionHandling.exception.UnAuthorizedAccessException;
import com.example.expenseTracker.user.model.User;
import com.example.expenseTracker.user.repository.UserRepository;
import com.example.expenseTracker.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findByIsCustomFalseOrUserId(userService.findCurrentUser().getId());
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findByIdAndIsCustomFalseOrIdAndUserId(id, id, userService.findCurrentUser().getId());
    }

    @Override
    public Category save(Category request) {
        Category category = Category.builder().
                name(request.getName()).
                user(userService.findCurrentUser()).
                isCustom(true).
                build();
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category request) {
        Optional<Category> dbCategory = categoryRepository.findById(request.getId());
        User user = userService.findCurrentUser();
        if (dbCategory.isPresent() && dbCategory.get().getUser().equals(user)) {
            Category category = Category.builder().
                    id(request.getId()).
                    name(request.getName()).
                    user(userService.findCurrentUser()).
                    isCustom(dbCategory.get().isCustom()).
                    build();
            return categoryRepository.save(category);
        } else
            throw new UnAuthorizedAccessException("Access Denied");
    }

    @Override
    public void delete(int id) {
        Optional<Category> dbCategory = categoryRepository.findById(id);
        if (dbCategory.isPresent() && dbCategory.get().getUser().equals(userService.findCurrentUser())) {
            categoryRepository.deleteById(id);
        } else
            throw new UnAuthorizedAccessException("Access Denied");
    }

}
