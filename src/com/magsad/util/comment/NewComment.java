package com.magsad.util.comment;
import com.magsad.model.Comment;
import com.magsad.repository.ArticleRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NewComment {
    private static ArticleRepository articleRepository = new ArticleRepository();
    public static Comment getNewComment(){
        Comment comment = new Comment();
        Scanner sc = new Scanner(System.in);
        System.out.print("id: ");
        comment.setId(sc.nextInt());
        System.out.print("name: ");
        comment.setName(sc.next());
        System.out.print("email: ");
        comment.setEmail(sc.next());
        System.out.print("text: ");
        comment.setText(sc.next());
        System.out.print("datePosted (example-20220123): ");
        String dateInString1 = sc.next();
        LocalDate date1 = LocalDate.parse(dateInString1, DateTimeFormatter.BASIC_ISO_DATE);
        comment.setDatePosted(date1);
        System.out.print("isReply (true/false): ");
        comment.setReply(sc.nextBoolean());
        System.out.print("publish (true/false): ");
        comment.setPublish(sc.nextBoolean());
//        articleId is in CommentService.java
        return comment;
    }
}
