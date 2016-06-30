package com.desj.configuration;

import com.desj.model.*;
import com.desj.service.LearningGroupService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Julien on 23.04.16.
 * This class creates demo content for the test database.
 */
@Configuration
@ComponentScan(basePackages = "com.desj")
@Transactional
public class TestDatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LearningGroupRepository learningGroupRepository;

    @Autowired
    private LearningGroupService learningGroupService;

    @Autowired
    private MCQuestionRepository mcQuestionRepository;

    @Autowired
    private GroupPostRepository groupPostRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // New learning groups
        LearningGroup group1 = new LearningGroup();
        group1.setName("Mathe Meister");
        group1.setSubject("Mathe");
        group1.setDescription("Mathehilfe für alle motivierten Erstis. Hier könnt ihr jegliche Fragen stellen die euch Probleme bereiten, wir versuchen sie gemeinsam zu lösen.");
        learningGroupRepository.save(group1);

        LearningGroup group2 = new LearningGroup();
        group2.setName("Statistik 1");
        group2.setSubject("Stat");
        group2.setDescription("Lerngruppe für alle Zweitsemester Wiwi und Winfo die Probleme und Fragen zu Statistik haben.");
        learningGroupRepository.save(group2);

        LearningGroup group3 = new LearningGroup();
        group3.setName("Programmentwicklung");
        group3.setSubject("PE");
        group3.setDescription("Lerngruppe für Programmierung und Programmentwicklung");
        learningGroupRepository.save(group3);

        LearningGroup group4 = new LearningGroup();
        group4.setName("Winfo");
        group4.setSubject("WI");
        group4.setDescription("Hier könnt ihr alle prüfungsrelevanten Themen wiederholen und falls ihr Fragen habt werden sie sicher beantwortet.");
        learningGroupRepository.save(group4);


        LearningGroup group5 = new LearningGroup();
        group5.setName("BWL und REWE");
        group5.setSubject("Betriebswirtschaft");
        group5.setDescription("Lerngruppe für alle die Probleme mit BWL und Rechnungswesen haben, hier könnt ihr Fragen stellen und gleichzeitig auch anderen helfen.");
        learningGroupRepository.save(group5);

        LearningGroup group6 = new LearningGroup();
        group6.setName("Zivilrecht");
        group6.setSubject("Recht");
        group6.setDescription("Hilfe für alle die Recht schreiben bzw. noch schreiben müssen, tauscht euch mit anderen aus um das Lernen leichter zu machen. ");
        learningGroupRepository.save(group6);

        LearningGroup group7 = new LearningGroup();
        group7.setName("Logik und Diskrete Strukturen");
        group7.setSubject("Logik");
        group7.setDescription("Wir sitzen alle im gleichen Boot und versuchen Logik gemeinsam zu meistern :)");
        learningGroupRepository.save(group7);


        // Creates new Desj user.
        // Julien
        com.desj.model.User julien = new com.desj.model.User();
        julien.setUsername("Julien Vollweiter");
        julien.setEmail("julien@mail.com");
        julien.setMajor("Winfo");
        userRepository.save(julien);
        learningGroupService.addMemberToLearningGroup(group6.getId(), julien);
        group6.setCreatorOfGroup(julien);
        learningGroupService.addMemberToLearningGroup(group7.getId(), julien);
        learningGroupService.addMemberToLearningGroup(group1.getId(), julien);

        // Desi
        com.desj.model.User desi = new com.desj.model.User();
        desi.setUsername("Desi Ivanova");
        desi.setMajor("Wirtschaftsinformatik");
        desi.setEmail("desi@mail.com");
        userRepository.save(desi);
        // Add the user to the learning group class
        learningGroupService.addMemberToLearningGroup(group1.getId(), desi);
        group1.setCreatorOfGroup(desi);
        learningGroupService.addMemberToLearningGroup(group2.getId(), desi);
        group2.setCreatorOfGroup(desi);
        learningGroupService.addMemberToLearningGroup(group4.getId(), desi);
        learningGroupService.addMemberToLearningGroup(group7.getId(), desi);

        // Sabrina
        com.desj.model.User sabrina = new com.desj.model.User();
        sabrina.setUsername("Sabrina Semmelmann");
        sabrina.setMajor("Winfo");
        sabrina.setEmail("sabrina@mail.com");
        userRepository.save(sabrina);
        learningGroupService.addMemberToLearningGroup(group4.getId(), sabrina);
        learningGroupService.addMemberToLearningGroup(group1.getId(), sabrina);
        learningGroupService.addMemberToLearningGroup(group2.getId(), sabrina);
        group4.setCreatorOfGroup(sabrina);

        // Erhan
        com.desj.model.User erhan = new com.desj.model.User();
        erhan.setUsername("Erhan Geyik");
        erhan.setMajor("Winfo");
        erhan.setEmail("erhan@mail.com");
        userRepository.save(erhan);
        // Add the user to the learning group class
        learningGroupService.addMemberToLearningGroup(group3.getId(), erhan);
        group3.setCreatorOfGroup(erhan);
        learningGroupService.addMemberToLearningGroup(group1.getId(), erhan);
        learningGroupService.addMemberToLearningGroup(group6.getId(), erhan);
        learningGroupService.addMemberToLearningGroup(group7.getId(), erhan);

        // Robert
        com.desj.model.User robert = new com.desj.model.User();
        robert.setUsername("Robert Rundhals");
        userRepository.save(robert);
        learningGroupService.addMemberToLearningGroup(group1.getId(), robert);
        learningGroupService.addMemberToLearningGroup(group2.getId(), robert);
        learningGroupService.addMemberToLearningGroup(group6.getId(), robert);

        // Friedrich
        com.desj.model.User friedrich = new com.desj.model.User();
        friedrich.setUsername("Friedrich Fröhlich");
        userRepository.save(friedrich);
        learningGroupService.addMemberToLearningGroup(group7.getId(), friedrich);
        group7.setCreatorOfGroup(friedrich);
        learningGroupService.addMemberToLearningGroup(group2.getId(), friedrich);
        learningGroupService.addMemberToLearningGroup(group3.getId(), friedrich);
        learningGroupService.addMemberToLearningGroup(group6.getId(), friedrich);

        // Leon
        com.desj.model.User leon = new com.desj.model.User();
        leon.setUsername("Leon Lässig");
        userRepository.save(leon);
        learningGroupService.addMemberToLearningGroup(group4.getId(), leon);
        learningGroupService.addMemberToLearningGroup(group7.getId(), leon);
        learningGroupService.addMemberToLearningGroup(group5.getId(), leon);
        learningGroupService.addMemberToLearningGroup(group1.getId(), leon);

        // Sarah
        com.desj.model.User sarah = new com.desj.model.User();
        sarah.setUsername("Sarah Müller");
        userRepository.save(sarah);
        learningGroupService.addMemberToLearningGroup(group5.getId(), sarah);
        group5.setCreatorOfGroup(sarah);
        learningGroupService.addMemberToLearningGroup(group2.getId(), sarah);

        // Anna
        com.desj.model.User anna = new com.desj.model.User();
        anna.setUsername("Anna Sommer");
        userRepository.save(anna);
        learningGroupService.addMemberToLearningGroup(group5.getId(), anna);
        learningGroupService.addMemberToLearningGroup(group1.getId(), anna);
        learningGroupService.addMemberToLearningGroup(group3.getId(), anna);

        // Franzi
        com.desj.model.User franzi = new com.desj.model.User();
        franzi.setUsername("Franzi Winter");
        userRepository.save(franzi);
        learningGroupService.addMemberToLearningGroup(group5.getId(), franzi);
        learningGroupService.addMemberToLearningGroup(group1.getId(), franzi);
        learningGroupService.addMemberToLearningGroup(group3.getId(), franzi);

        // Tim
        com.desj.model.User tim = new com.desj.model.User();
        tim.setUsername("Tim Fritz");
        userRepository.save(tim);
        learningGroupService.addMemberToLearningGroup(group5.getId(), tim);
        learningGroupService.addMemberToLearningGroup(group1.getId(), tim);
        learningGroupService.addMemberToLearningGroup(group3.getId(), tim);
        learningGroupService.addMemberToLearningGroup(group7.getId(), tim);

        // Karl
        com.desj.model.User karl = new com.desj.model.User();
        karl.setUsername("Karl Friedrich");
        userRepository.save(karl);
        learningGroupService.addMemberToLearningGroup(group5.getId(), karl);
        learningGroupService.addMemberToLearningGroup(group1.getId(), karl);
        learningGroupService.addMemberToLearningGroup(group3.getId(), karl);
        learningGroupService.addMemberToLearningGroup(group7.getId(), karl);

        // Hans
        com.desj.model.User hans = new com.desj.model.User();
        hans.setUsername("Hans Friedrich");
        userRepository.save(hans);
        learningGroupService.addMemberToLearningGroup(group5.getId(), hans);
        learningGroupService.addMemberToLearningGroup(group1.getId(), hans);
        learningGroupService.addMemberToLearningGroup(group3.getId(), hans);
        learningGroupService.addMemberToLearningGroup(group7.getId(), hans);

        // Nina
        com.desj.model.User nina = new com.desj.model.User();
        nina.setUsername("Nina Schwarz");
        userRepository.save(nina);
        learningGroupService.addMemberToLearningGroup(group1.getId(), nina);
        learningGroupService.addMemberToLearningGroup(group2.getId(), nina);
        learningGroupService.addMemberToLearningGroup(group4.getId(), nina);
        learningGroupService.addMemberToLearningGroup(group6.getId(), nina);
        learningGroupService.addMemberToLearningGroup(group7.getId(), nina);

        // Max
        com.desj.model.User max = new com.desj.model.User();
        max.setUsername("Max Meier");
        userRepository.save(max);
        learningGroupService.addMemberToLearningGroup(group2.getId(), max);
        learningGroupService.addMemberToLearningGroup(group4.getId(), max);
        learningGroupService.addMemberToLearningGroup(group6.getId(), max);
        learningGroupService.addMemberToLearningGroup(group7.getId(), max);

        // Paula
        com.desj.model.User paula = new com.desj.model.User();
        paula.setUsername("Paula Schulze");
        userRepository.save(paula);
        learningGroupService.addMemberToLearningGroup(group3.getId(), paula);
        learningGroupService.addMemberToLearningGroup(group4.getId(), paula);
        learningGroupService.addMemberToLearningGroup(group6.getId(), paula);
        learningGroupService.addMemberToLearningGroup(group1.getId(), paula);

        // Omar
        com.desj.model.User omar = new com.desj.model.User();
        omar.setUsername("Omar Karim");
        userRepository.save(omar);
        learningGroupService.addMemberToLearningGroup(group2.getId(), omar);
        learningGroupService.addMemberToLearningGroup(group5.getId(), omar);
        learningGroupService.addMemberToLearningGroup(group7.getId(), omar);
        learningGroupService.addMemberToLearningGroup(group1.getId(), omar);

        // Ali
        com.desj.model.User ali = new com.desj.model.User();
        ali.setUsername("Ali Muhammed");
        userRepository.save(ali);
        learningGroupService.addMemberToLearningGroup(group3.getId(), ali);
        learningGroupService.addMemberToLearningGroup(group4.getId(), ali);
        learningGroupService.addMemberToLearningGroup(group6.getId(), ali);
        learningGroupService.addMemberToLearningGroup(group1.getId(), ali);

        // Maria
        com.desj.model.User maria = new com.desj.model.User();
        maria.setUsername("Maria Iliadis");
        userRepository.save(maria);
        learningGroupService.addMemberToLearningGroup(group3.getId(), maria);
        learningGroupService.addMemberToLearningGroup(group5.getId(), maria);
        learningGroupService.addMemberToLearningGroup(group7.getId(), maria);
        learningGroupService.addMemberToLearningGroup(group2.getId(), maria);

        // Pedro
        com.desj.model.User pedro = new com.desj.model.User();
        pedro.setUsername("Pedro Rodriguez");
        userRepository.save(pedro);
        learningGroupService.addMemberToLearningGroup(group3.getId(), pedro);
        learningGroupService.addMemberToLearningGroup(group5.getId(), pedro);
        learningGroupService.addMemberToLearningGroup(group7.getId(), pedro);
        learningGroupService.addMemberToLearningGroup(group2.getId(), pedro);


        // MC Questions
        MCQuestion mcQuestion1 = new MCQuestion();
        mcQuestion1.setQuestion("Frage 1");
        mcQuestion1.setAnswerA("Antwort A");
        mcQuestion1.setAnswerB("Antwort B");
        mcQuestion1.setAnswerC("Antwort C");
        mcQuestion1.setAnswerD("Antwort D");
        mcQuestion1.setCorrespondingLearningGroup(group1);
        mcQuestion1.setCreator(pedro);
        mcQuestion1.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion1);

        MCQuestion mcQuestion2 = new MCQuestion();
        mcQuestion2.setQuestion("Frage 2");
        mcQuestion2.setAnswerA("Antwort A");
        mcQuestion2.setAnswerB("Antwort B");
        mcQuestion2.setAnswerC("Antwort C");
        mcQuestion2.setAnswerD("Antwort D");
        mcQuestion2.setCorrespondingLearningGroup(group1);
        mcQuestion2.setCreator(pedro);
        mcQuestion2.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion2);

        MCQuestion mcQuestion3 = new MCQuestion();
        mcQuestion3.setQuestion("Frage 3");
        mcQuestion3.setAnswerA("Antwort A");
        mcQuestion3.setAnswerB("Antwort B");
        mcQuestion3.setAnswerC("Antwort C");
        mcQuestion3.setAnswerD("Antwort D");
        mcQuestion3.setCorrespondingLearningGroup(group1);
        mcQuestion3.setCreator(pedro);
        mcQuestion3.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion3);

        MCQuestion mcQuestion4 = new MCQuestion();
        mcQuestion4.setQuestion("Frage 4");
        mcQuestion4.setAnswerA("Antwort A");
        mcQuestion4.setAnswerB("Antwort B");
        mcQuestion4.setAnswerC("Antwort C");
        mcQuestion4.setAnswerD("Antwort D");
        mcQuestion4.setCorrespondingLearningGroup(group1);
        mcQuestion4.setCreator(pedro);
        mcQuestion4.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion4);

        MCQuestion mcQuestion5 = new MCQuestion();
        mcQuestion5.setQuestion("Frage 5");
        mcQuestion5.setAnswerA("Antwort A");
        mcQuestion5.setAnswerB("Antwort B");
        mcQuestion5.setAnswerC("Antwort C");
        mcQuestion5.setAnswerD("Antwort D");
        mcQuestion5.setCorrespondingLearningGroup(group1);
        mcQuestion5.setCreator(pedro);
        mcQuestion5.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion5);

        MCQuestion mcQuestion6 = new MCQuestion();
        mcQuestion6.setQuestion("Frage 6");
        mcQuestion6.setAnswerA("Antwort A");
        mcQuestion6.setAnswerB("Antwort B");
        mcQuestion6.setAnswerC("Antwort C");
        mcQuestion6.setAnswerD("Antwort D");
        mcQuestion6.setCorrespondingLearningGroup(group1);
        mcQuestion6.setCreator(pedro);
        mcQuestion6.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion6);

        MCQuestion mcQuestion7 = new MCQuestion();
        mcQuestion7.setQuestion("Frage 7");
        mcQuestion7.setAnswerA("Antwort A");
        mcQuestion7.setAnswerB("Antwort B");
        mcQuestion7.setAnswerC("Antwort C");
        mcQuestion7.setAnswerD("Antwort D");
        mcQuestion7.setCorrespondingLearningGroup(group1);
        mcQuestion7.setCreator(pedro);
        mcQuestion7.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion7);

        MCQuestion mcQuestion8 = new MCQuestion();
        mcQuestion8.setQuestion("Frage 8");
        mcQuestion8.setAnswerA("Antwort A");
        mcQuestion8.setAnswerB("Antwort B");
        mcQuestion8.setAnswerC("Antwort C");
        mcQuestion8.setAnswerD("Antwort D");
        mcQuestion8.setCorrespondingLearningGroup(group1);
        mcQuestion8.setCreator(pedro);
        mcQuestion8.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion8);

        MCQuestion mcQuestion9 = new MCQuestion();
        mcQuestion9.setQuestion("Frage 9");
        mcQuestion9.setAnswerA("Antwort A");
        mcQuestion9.setAnswerB("Antwort B");
        mcQuestion9.setAnswerC("Antwort C");
        mcQuestion9.setAnswerD("Antwort D");
        mcQuestion9.setCorrespondingLearningGroup(group1);
        mcQuestion9.setCreator(pedro);
        mcQuestion9.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion9);

        MCQuestion mcQuestion10 = new MCQuestion();
        mcQuestion10.setQuestion("Frage 10");
        mcQuestion10.setAnswerA("Antwort A");
        mcQuestion10.setAnswerB("Antwort B");
        mcQuestion10.setAnswerC("Antwort C");
        mcQuestion10.setAnswerD("Antwort D");
        mcQuestion10.setCorrespondingLearningGroup(group1);
        mcQuestion10.setCreator(pedro);
        mcQuestion10.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion10);


        // Group posts
        com.desj.model.GroupPost groupPost1 = new com.desj.model.GroupPost();
        groupPost1.setAssociatedUser(desi);
        groupPost1.setText("Herzlich Willkommen in der Mathe-Lerngruppe. Nutzt das Forum für Fragen und Antworten.");
        groupPost1.setAssociatedLearningGroup(group1);
        groupPostRepository.save(groupPost1);

        com.desj.model.GroupPost groupPost2 = new com.desj.model.GroupPost();
        groupPost2.setAssociatedUser(desi);
        groupPost2.setText("Hallo an alle Nutzer aus der Statistik-Lerngruppe, hier könnt Ihr eure Fragen schreiben und bekommt Antworten.");
        groupPost2.setAssociatedLearningGroup(group2);
        groupPostRepository.save(groupPost2);

        com.desj.model.GroupPost groupPost3 = new com.desj.model.GroupPost();
        groupPost3.setAssociatedUser(erhan);
        groupPost3.setText("Habt Ihr Fragen zu PE - Dann stellt Sie hier!");
        groupPost3.setAssociatedLearningGroup(group3);
        groupPostRepository.save(groupPost3);

        com.desj.model.GroupPost groupPost4 = new com.desj.model.GroupPost();
        groupPost4.setAssociatedUser(paula);
        groupPost4.setText("Könnte mir jemand die ER-Diagramme erklären?");
        groupPost4.setAssociatedLearningGroup(group4);
        groupPostRepository.save(groupPost4);

        com.desj.model.GroupPost groupPost5 = new com.desj.model.GroupPost();
        groupPost5.setAssociatedUser(sarah);
        groupPost5.setText("Wer Hilfe benötigt bei BWL, kann hier gerne Fragen.");
        groupPost5.setAssociatedLearningGroup(group5);
        groupPostRepository.save(groupPost5);

        com.desj.model.GroupPost groupPost6 = new com.desj.model.GroupPost();
        groupPost6.setAssociatedUser(ali);
        groupPost6.setText("Hilfe Leute, hat einer ein Gesetzbuch für mich?!?!?!?");
        groupPost6.setAssociatedLearningGroup(group6);
        groupPostRepository.save(groupPost6);

        com.desj.model.GroupPost groupPost7 = new com.desj.model.GroupPost();
        groupPost7.setAssociatedUser(friedrich);
        groupPost7.setText("Kann mir einer bei der zweiten Abgabe helfen?");
        groupPost7.setAssociatedLearningGroup(group7);
        groupPostRepository.save(groupPost7);

        com.desj.model.GroupPost groupPost8 = new com.desj.model.GroupPost();
        groupPost8.setAssociatedUser(friedrich);
        groupPost8.setText("Kann mir jemand bei Buchungssätzen helfen? Ich versteh da leider gar nichts... ");
        groupPost8.setAssociatedLearningGroup(group5);
        groupPostRepository.save(groupPost8);

        com.desj.model.GroupPost groupPost9 = new com.desj.model.GroupPost();
        groupPost9.setAssociatedUser(anna);
        groupPost9.setText("Weiß einer wie Refactoring funktioniert?");
        groupPost9.setAssociatedLearningGroup(group3);
        groupPostRepository.save(groupPost9);

        com.desj.model.GroupPost groupPost10 = new com.desj.model.GroupPost();
        groupPost10.setAssociatedUser(nina);
        groupPost10.setText("Gibt es eine leichte, unkomplizierte Erklärung zu partieller Ableitung?");
        groupPost10.setAssociatedLearningGroup(group1);
        groupPostRepository.save(groupPost10);


        // Create and add Comments
        com.desj.model.Comment comment1 = new com.desj.model.Comment();
        comment1.setAssociatedGroupPost(groupPost7);
        comment1.setCreator(pedro);
        comment1.setText("Klar, wo hakt's denn? ");
        commentRepository.save(comment1);

        com.desj.model.Comment comment2 = new com.desj.model.Comment();
        comment2.setAssociatedGroupPost(groupPost7);
        comment2.setCreator(friedrich);
        comment2.setText("Aufgabe 2, das mit der Resolution.");
        commentRepository.save(comment2);

        com.desj.model.Comment comment3 = new com.desj.model.Comment();
        comment3.setAssociatedGroupPost(groupPost6);
        comment3.setCreator(max);
        comment3.setText("Ja klar <a title=\"klick\" href=\"https://www.amazon.de/Bürgerliches-Gesetzbuch-Gleichbehandlungsgesetz-BGB-Informationspflichten-Verordnung-Einführungsgesetz/dp/3423050012/ref=sr_1_1?ie=UTF8&qid=1467292152&sr=8-1&keywords=bgb\">hier</a>!");
        commentRepository.save(comment3);

        com.desj.model.Comment comment5 = new com.desj.model.Comment();
        comment5.setAssociatedGroupPost(groupPost8);
        comment5.setCreator(omar);
        comment5.setText("Ja klar, schick einfach mal die Beispiele dann schau ich's mir an.");
        commentRepository.save(comment5);

        com.desj.model.Comment comment6 = new com.desj.model.Comment();
        comment6.setAssociatedGroupPost(groupPost9);
        comment6.setCreator(karl);
        comment6.setText("Ne leider nicht, wäre super wenn's jemand erklären könnte.");
        commentRepository.save(comment6);

        com.desj.model.Comment comment7 = new com.desj.model.Comment();
        comment7.setAssociatedGroupPost(groupPost9);
        comment7.setCreator(ali);
        comment7.setText("Wir können uns nach der Vorlesung mal treffen, dann erkläre ich es euch :)");
        commentRepository.save(comment7);

        com.desj.model.Comment comment8 = new com.desj.model.Comment();
        comment8.setAssociatedGroupPost(groupPost10);
        comment8.setCreator(hans);
        comment8.setText("Ich kann es dir gerne bei einem Kaffee erklären ;)");
        commentRepository.save(comment8);


        // Set authorities.
        Collection<GrantedAuthority> AdminAuthorities = new ArrayList<>();
        AdminAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        Collection<GrantedAuthority> UserAuthorities = new ArrayList<>();
        UserAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));


        // Creates new spring security user. These infos are merged with the Desj user data
        // within the UserService.java
        User adminJulien = new User("julien@mail.com", encoder.encode("1234"), AdminAuthorities);
        userDetailsManager.createUser(adminJulien);

        User adminDesi = new User("desi@mail.com", encoder.encode("1234"), AdminAuthorities);
        userDetailsManager.createUser(adminDesi);

        User adminSabrina = new User("sabrina@mail.com", encoder.encode("1234"), AdminAuthorities);
        userDetailsManager.createUser(adminSabrina);

        User adminErhan = new User("erhan@mail.com", encoder.encode("1234"), AdminAuthorities);
        userDetailsManager.createUser(adminErhan);

        User userRobert = new User("robert@rundhals.com", encoder.encode("1234"), UserAuthorities);
        userDetailsManager.createUser(userRobert);
    }
}
