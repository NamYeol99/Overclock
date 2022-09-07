package overclock.overclock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import overclock.overclock.dto.PageRequestDTO;
import overclock.overclock.dto.PostsDTO;
import overclock.overclock.service.PostsService;

@Controller
@RequestMapping("/posts")
@Log4j2
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;


    //중고거래 컨트롤러
    @GetMapping("/mregister")
    public void mregister(){
        log.info("register get...");
    }
    @PostMapping("/mregister")
    public String registerPost(PostsDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto... {}", dto);

        Long id = postsService.mregister(dto);
        log.info("id {}", id);

        redirectAttributes.addFlashAttribute("msg", id);

        return "redirect:/posts/mregister";
    }

    //부품게시판 컨트롤러
    @GetMapping("/pregister")
    public void pregister(){
        log.info("register get...");
    }
    @PostMapping("/pregister")
    public String pregisterPost(PostsDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto... {}", dto);

        Long id = postsService.pregister(dto);
        log.info("id {}", id);

        redirectAttributes.addFlashAttribute("msg", id);

        return "redirect:/posts/pregister";
    }

    //목록처리
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list.... {} ", pageRequestDTO);
        model.addAttribute("result", postsService.getList(pageRequestDTO));
    }

    //조회처리
    @GetMapping("/read")
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long id, Model model){
        log.info("id: " + id);
        PostsDTO postsDTO = postsService.get(id);
        log.info(postsDTO);
        model.addAttribute("dto", postsDTO);
    }

}