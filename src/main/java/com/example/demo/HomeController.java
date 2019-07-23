package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String homeList(Model model){
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "home";
    }

    @GetMapping("/addcar")
    public String carForm(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @GetMapping("/addcategory")
    public String categoryForm(Model model){
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @PostMapping("/process/car")
    public String processCar(@Valid Car car, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("categories", categoryRepository.findAll());
            return "carform";
        }
        carRepository.save(car);
        return "redirect:/";
    }

    @PostMapping("/process/category")
    public String processCategory(@Valid Category category, BindingResult result, Model model){
        if (result.hasErrors()){
            return "catergoryform";
        }
        if (categoryRepository.findBycategoryName(category.getCategoryName()) != null){
            model.addAttribute("message", "You already have a category by that name!");
            return "categoryform";
        }
        categoryRepository.save(category);
        return "redirect:/";
    }

    @RequestMapping("/car/details/{id}")
    public String showCarDetails(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("car", carRepository.findById(id).get());
        return "showcardetial";
    }

    @RequestMapping("/car/update/{id}")
    public String updateCarDetails(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("car", carRepository.findById(id).get());
        return "carform";
    }

    @RequestMapping("/car/delete/{id}")
    public String deleteCar(@PathVariable("id") long id)
    {
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @PostConstruct
    public void filltables(){

        Category category = new Category();


        Car car = new Car("Subaru","Outback",2015, 30000);
        car.setCategory(category);

    }
}

}
