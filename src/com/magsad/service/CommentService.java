package com.magsad.service;

import com.magsad.model.Comment;
import com.magsad.repository.ArticleRepository;
import com.magsad.repository.CommentRepository;
import com.magsad.util.comment.NewComment;
import com.magsad.util.comment.UpdateComment;

import java.util.List;
import java.util.Scanner;

public class CommentService {
    private static CommentRepository commentRepository = new CommentRepository();
    private static ArticleRepository articleRepository = new ArticleRepository();
    public void getComment() {
        do {
            System.out.println("\n1.New 2.FindAll 3.FindById 4.Delete 5.Update 0.Exit");
            System.out.print("Enter Main/Comment select: ");
            int select = new Scanner(System.in).nextInt();
            if (select == 5) {
                update();
            }
            if (select == 4) {
                delete();
            }
            if (select == 3) {
                findById();
            }
            if (select == 2) {
                findAll();
            }
            if (select == 1) {
                save();
            }
            if (select == 0) {
                break;
            }
        } while (true);
    }

    private static void findAll() {
        List<Comment> commentList = commentRepository.findAll();
        commentList.forEach(comment -> System.out.print(getPrint(comment)));
    }

    private static void findById() {
        System.out.print("Enter comment id: ");
        int id = new Scanner(System.in).nextInt();
        Comment comment = commentRepository.findById(id);
        System.out.println(getPrint(comment));
    }

    private static void save() {
        Comment comment = NewComment.getNewComment();
        System.out.print("articleId: ");
        comment.setArticle(articleRepository.findById(new Scanner(System.in).nextInt()));
        System.out.println(commentRepository.save(comment));
    }

    private static void update() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter comment id: ");
        int id = sc.nextInt();
        Comment comment = commentRepository.findById(id);
        System.out.println(getPrint(comment));
        UpdateComment.updateComment(comment);
        System.out.println(commentRepository.update(comment));
    }

    private static void delete() {
        System.out.print("Enter comment id: ");
        int id = new Scanner(System.in).nextInt();
       Comment comment = commentRepository.findById(id);
        commentRepository.delete(comment);
    }

    private static String getPrint(Comment comment) {
        return String.format("%4d | %20s |%20s |%30s |%10s |%5b |%5b |%4d |\n",
                comment.getId(),
                comment.getName(),
                comment.getEmail(),
                comment.getText(),
                comment.getDatePosted(),
                comment.getReply(),
                comment.getPublish(),
                comment.getArticle().getId()
        );
    }
}
