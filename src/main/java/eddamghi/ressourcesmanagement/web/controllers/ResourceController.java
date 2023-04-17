package eddamghi.ressourcesmanagement.web.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import eddamghi.ressourcesmanagement.DAO.entities.Resource;
import eddamghi.ressourcesmanagement.DAO.repositories.ResourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@AllArgsConstructor
public class ResourceController {
    private ResourceRepository resourceRepository;
    @GetMapping("/index")
    public String resources(Model model,
                            @RequestParam(name = "page", defaultValue = "1") int page,
                            @RequestParam(name = "size", defaultValue = "5")int size,
                            @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Resource> resources = resourceRepository.findByTitleContains(keyword, PageRequest.of(page, size));
        model.addAttribute("resources", resources.getContent());
        model.addAttribute("pages", new int[resources.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "resources";
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }
    @GetMapping("/delete")
    public String deleteResource(Long id, String keyword, int page){
        resourceRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/edit")
    public String editResource(@RequestParam(name = "id") Long id, Model model){
        Resource resource=resourceRepository.findById(id).get();
        model.addAttribute("resource",resource);
        return "editResource";
    }
    @GetMapping("/admin/add")
    public String addResource(Model model){
        model.addAttribute("resource", new Resource());
        return "addResource";
    }
    @GetMapping("/admin/save")
    public String saveResource(Resource resource){
        resourceRepository.save(resource);
        return "addResource";
    }
}
