package com.magsad.util.comment;
import com.magsad.model.Article;
import com.magsad.model.Comment;
import com.magsad.repository.ArticleRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UpdateComment {
    private static ArticleRepository articleRepository = new ArticleRepository();
    public static Comment updateComment(Comment comment) {
        do {
            System.out.println(" 1.Name 2.Email 3.Text 4.DatePosted 5.IsReply 6.Publish 7.ArticleId  0.Exit");
            System.out.print("Enter Comment/Update select: ");
            Scanner sc = new Scanner(System.in);
            int select = sc.nextInt();
            if (select == 7) {
                System.out.print("articleId: ");
                comment.setArticle(articleRepository.findById(sc.nextInt()));
            }
            if (select == 6) {
                System.out.print("publish (true/false): ");
                comment.setPublish(sc.nextBoolean());
            }
            if (select == 5) {
                System.out.print("isReply (true/false): ");
                comment.setReply(sc.nextBoolean());
            }
            if (select == 4) {
                System.out.print("datePosted (example-20220123): ");
                String dateInString1 = sc.next();
                LocalDate date1 = LocalDate.parse(dateInString1, DateTimeFormatter.BASIC_ISO_DATE);
                comment.setDatePosted(date1);
            }
            if (select == 3) {
                System.out.print("text: ");
                comment.setText(sc.next());
            }
            if (select == 2) {
                System.out.print("email: ");
                comment.setEmail(sc.next());
            }
            if (select == 1) {
                System.out.print("name: ");
                comment.setName(sc.next());
            }
            if (select == 0) {
                return comment;
            }
        } while (true);

    }
}
