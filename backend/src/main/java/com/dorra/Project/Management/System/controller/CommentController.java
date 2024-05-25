package com.dorra.Project.Management.System.controller;

import com.dorra.Project.Management.System.modal.Comment;
import com.dorra.Project.Management.System.modal.User;
import com.dorra.Project.Management.System.request.CreateCommentRequest;
import com.dorra.Project.Management.System.response.MessageResponse;
import com.dorra.Project.Management.System.service.CommentService;
import com.dorra.Project.Management.System.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Comment>createComment(@RequestBody CreateCommentRequest req, @RequestHeader ("Authorization")String jwt)throws  Exception{
        User user=userService.findUserProfileByJwt(jwt);
        Comment createdComment =commentService.createComment(req.getIssueId(),user.getId(),req.getContent());
        return  new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse>deleteComment(@PathVariable Long commentId,@RequestHeader ("Authorization")String jwt)throws Exception{
        User user=userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        MessageResponse res=new MessageResponse();
        res.setMessage("comment deletes successfully");
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>>getCommentsByIssueId(@PathVariable Long isueId){
        List<Comment>comments=commentService.findCommentByIssueId(isueId);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }



}
