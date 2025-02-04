package zerobase.weather.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;

@Controller
// 컨트롤러는 api와 맞닿아 있음
//어떤 api를 만들지 고민
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/create/diary")//create/diaryd요청을 내면 creatediary함수가 동작한다.
    void createDiray(@RequestParam  @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody  String text) {
        // RequestParam <= 요청을 보낼떄 넣어주는 파라미터
        diaryService.createDiary(date,text);


    }
}
