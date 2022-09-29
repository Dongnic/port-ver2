package com.im.port.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.im.port.service.UserServiceImpl;
import com.im.port.vo.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

     //전체 조회
     @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<?> getUserList() throws Exception {
        System.out.println("======================전체조회 Get=========================");
        List<UserDto> dtoList = userService.getUserList();
         return ResponseEntity.ok(dtoList);
     }
     
    //등록
    @PostMapping
    public ResponseEntity<String> postUser(@RequestBody UserDto userDto) throws Exception {
        System.out.println("======================등록 Post=========================");
        System.out.println("입력된 userDto: "+userDto);
        // 패스워드 암호화
        String encPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encPassword);
        userDto.setRole("MEMBER");
        userService.postUser(userDto);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    //수정
    @PutMapping("/{id}")
    public ResponseEntity<String> putTodo(@PathVariable("id") Long id, @RequestBody UserDto userDto) throws Exception {
        System.out.println("======================수정 Put=========================");
        System.out.println("입력된 userDto: "+userDto);
        System.out.println("입력된 id: "+id);
        UserDto isChecked = userService.findUserById(id);
        if(isChecked == null){
            return new ResponseEntity<>("Fail", HttpStatus.NOT_FOUND);
        }
        userService.postUser(userDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) throws Exception {
        System.out.println("======================삭제 Delete=========================");
        System.out.println("입력된 id: "+id);
        userService.deleteUser(id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //단일 조회
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTodo(@PathVariable("id") Long id) throws Exception {
        System.out.println("======================1건 조회 Get=========================");
        System.out.println("입력된 id: "+id);
        UserDto userDto = userService.findUserById(id);
        return ResponseEntity.ok(userDto);
    }
















    // @ResponseBody
    // @RequestMapping("/getUser")
    // public User getUser(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails) {
    //     System.out.println("getUser");
    //     System.out.println("getUser"+authentication.getPrincipal());
    //     System.out.println("getUser"+userDetails.getUser());
    //     return userDetails.getUser();
    // }
    // @ResponseBody
    // @RequestMapping("/getServer")
    // public List<Chatuser> getServer(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails) {
    //     System.out.println("getServer");
    //     System.out.println("getUser"+userDetails.getUser());
    //     System.out.println("getUserID"+userDetails.getUser().getId());
    //     int user_id = userDetails.getUser().getId();
    //     return chatuserRepository.findByUserid(String.valueOf(user_id));
    // }
    // @RequestMapping("/join")
    // public String join(User user) {
    //     System.out.println("======================join=========================");
    //     System.out.println("입력된 값: "+user);
    //     // 패스워드 암호화
    //     String encPassword = bCryptPasswordEncoder.encode(user.getPassword());
    //     // user.setPassword(encPassword);
    //     // user.setRole("MEMBER");
    //     User ruser = User.builder()
    //     .email(user.getEmail())
    //     .username(user.getUsername())
    //     .password(encPassword)
    //     .role("MEMBER")
    //     .build();
    //     System.out.println("마지막 값: "+ruser);
    //     userRepository.save(ruser); // 회원가입은 잘되는데 1234 => 시큐리티 로그인 안됨 패스워드가 암호화가 되어있지 않기 때문에
    //     return "redirect:/joinDone";
    // }

    // @Secured("ROLE_ADMIN")
    // @GetMapping("/info")
    // public @ResponseBody String info() {
    //     return "개인정보";
    // }

    // @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    // @GetMapping("/data")
    // public @ResponseBody String data() {
    //     return "데이터정보";
    // }
}
