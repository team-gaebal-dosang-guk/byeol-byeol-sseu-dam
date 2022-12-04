package com.app.byeolbyeolsseudam.controller.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.service.program.ProgramService;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pro/*")
public class ProgramRestController {
    private final ProgramService programService;

    /* default List _ 프로그램 들어갔을 때 실행 / 전체 클릭했을 때 실행  */
    @GetMapping("/list")
    public List<ProgramDTO> getList(){
        List<ProgramDTO> programs = programService.programAllList();
        return programs;
    }

    /* Status List _ 프로그램 상태 클릭시 실행되는 List */
    @GetMapping("/list/{programStatus}")
    public List<ProgramDTO> statusList(@PathVariable ProgramStatus programStatus){
        List<ProgramDTO> programs = programService.programStatusIngList(programStatus);
        return programs;
    }

    /* Keyword List _ 검색시 실행되는 List */
    @PostMapping("/list/{keyword}")
    public List<ProgramDTO> searchKeywordList(@PathVariable String keyword){
        List<ProgramDTO> programs = programService.searchProgram(keyword);
        return programs;
    }

}
