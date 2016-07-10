package com.desj.configuration;

import com.desj.model.*;
import com.desj.service.LearningGroupService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien on 23.04.16.
 * This class creates demo content for the test database.
 */
@Configuration
@ComponentScan(basePackages = "com.desj")
@Transactional
public class TestDatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

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

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionReposiory questionReposiory;

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
        group3.setName("Programmierung und Softwareentwicklung");
        group3.setSubject("PSE");
        group3.setDescription("Lerngruppe für Programmierung und Softwareentwicklung");
        learningGroupRepository.save(group3);

        LearningGroup group4 = new LearningGroup();
        group4.setName("Wirtschaftsinformatik");
        group4.setSubject("WINFO");
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
        group7.setName("Datenstrukturen und Algorithmen");
        group7.setSubject("DSA");
        group7.setDescription("Wir sitzen alle im gleichen Boot und versuchen DSA gemeinsam zu meistern :)");
        learningGroupRepository.save(group7);

        LearningGroup group8 = new LearningGroup();
        group8.setName("Softwarepraktikum");
        group8.setSubject("SOPRA");
        group8.setDescription("Ihr seid im 4. Semester und sucht die Sopra-Lerngruppe? Dann seid ihr hier genau richtig, tretet bei!");
        learningGroupRepository.save(group8);


        // Creates new Desj user.
        // Julien
        com.desj.model.User julien = new com.desj.model.User();
        julien.setUsername("Julien Vollweiter");
        julien.setEmail("julien@mail.com");
        julien.setPassword("1234");
        julien.setMajor("Winfo");
        userService.save(julien);
        learningGroupService.addMemberToLearningGroup(group6.getId(), julien);
        group6.setCreatorOfGroup(julien);
        learningGroupService.addMemberToLearningGroup(group7.getId(), julien);
        learningGroupService.addMemberToLearningGroup(group1.getId(), julien);
        learningGroupService.addMemberToLearningGroup(group8.getId(), julien);


        // Desi
        com.desj.model.User desi = new com.desj.model.User();
        desi.setUsername("Desi Ivanova");
        desi.setMajor("Wirtschaftsinformatik");
        desi.setEmail("desi@mail.com");
        desi.setPassword("1234");
        userService.save(desi);
        // Add the user to the learning group class
        learningGroupService.addMemberToLearningGroup(group1.getId(), desi);
        group1.setCreatorOfGroup(desi);
        learningGroupService.addMemberToLearningGroup(group2.getId(), desi);
        group2.setCreatorOfGroup(desi);
        learningGroupService.addMemberToLearningGroup(group4.getId(), desi);
        learningGroupService.addMemberToLearningGroup(group7.getId(), desi);
        learningGroupService.addMemberToLearningGroup(group8.getId(), desi);


        // Sabrina
        com.desj.model.User sabrina = new com.desj.model.User();
        sabrina.setUsername("Sabrina Semmelmann");
        sabrina.setMajor("Winfo");
        sabrina.setEmail("sabrina@mail.com");
        sabrina.setPassword("1234");
        userService.save(sabrina);
        learningGroupService.addMemberToLearningGroup(group4.getId(), sabrina);
        learningGroupService.addMemberToLearningGroup(group1.getId(), sabrina);
        learningGroupService.addMemberToLearningGroup(group2.getId(), sabrina);
        learningGroupService.addMemberToLearningGroup(group8.getId(), sabrina);
        group4.setCreatorOfGroup(sabrina);

        // Erhan
        com.desj.model.User erhan = new com.desj.model.User();
        erhan.setUsername("Erhan Geyik");
        erhan.setMajor("Winfo");
        erhan.setEmail("erhan@mail.com");
        erhan.setPassword("1234");
        userService.save(erhan);
        // Add the user to the learning group class
        learningGroupService.addMemberToLearningGroup(group3.getId(), erhan);
        group3.setCreatorOfGroup(erhan);
        group8.setCreatorOfGroup(erhan);
        learningGroupService.addMemberToLearningGroup(group1.getId(), erhan);
        learningGroupService.addMemberToLearningGroup(group6.getId(), erhan);
        learningGroupService.addMemberToLearningGroup(group7.getId(), erhan);
        learningGroupService.addMemberToLearningGroup(group8.getId(), erhan);


        // Robert
        com.desj.model.User robert = new com.desj.model.User();
        robert.setUsername("Robert Rundhals");
        userRepository.save(robert);
        learningGroupService.addMemberToLearningGroup(group1.getId(), robert);
        learningGroupService.addMemberToLearningGroup(group2.getId(), robert);
        learningGroupService.addMemberToLearningGroup(group6.getId(), robert);
        learningGroupService.addMemberToLearningGroup(group8.getId(), robert);


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
        learningGroupService.addMemberToLearningGroup(group8.getId(), sarah);


        // Anna
        com.desj.model.User anna = new com.desj.model.User();
        anna.setUsername("Anna Sommer");
        userRepository.save(anna);
        learningGroupService.addMemberToLearningGroup(group5.getId(), anna);
        learningGroupService.addMemberToLearningGroup(group1.getId(), anna);
        learningGroupService.addMemberToLearningGroup(group3.getId(), anna);
        learningGroupService.addMemberToLearningGroup(group8.getId(), anna);


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
        learningGroupService.addMemberToLearningGroup(group8.getId(), hans);


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
        learningGroupService.addMemberToLearningGroup(group8.getId(), max);


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
        // Group 1 - Mathe
        MCQuestion mcQuestion1 = new MCQuestion();
        mcQuestion1.setQuestion("Aus a < b und b < c folgt ..?");
        mcQuestion1.setAnswerA(" a < c");
        mcQuestion1.setAnswerB(" a <= c");
        mcQuestion1.setAnswerC(" a = c");
        mcQuestion1.setAnswerD(" a > c");
        mcQuestion1.setCorrespondingLearningGroup(group1);
        mcQuestion1.setCreator(pedro);
        mcQuestion1.setCorrectAnswers("A");
        mcQuestionRepository.save(mcQuestion1);

        MCQuestion mcQuestion2 = new MCQuestion();
        mcQuestion2.setQuestion("(a + b)² = ..");
        mcQuestion2.setAnswerA("a² + b² + ab");
        mcQuestion2.setAnswerB("a² + 2ab + b²");
        mcQuestion2.setAnswerC("ab²");
        mcQuestion2.setAnswerD("(a+b) * (a+b)");
        mcQuestion2.setCorrespondingLearningGroup(group1);
        mcQuestion2.setCreator(pedro);
        mcQuestion2.setCorrectAnswers("B,D");
        mcQuestionRepository.save(mcQuestion2);

        MCQuestion mcQuestion3 = new MCQuestion();
        mcQuestion3.setQuestion("tan(α) setzt sich zusammen aus .. ? ");
        mcQuestion3.setAnswerA("sin(α) / cos(α)");
        mcQuestion3.setAnswerB("Hypotenuse / Ankathete");
        mcQuestion3.setAnswerC("Gegenkathete / Ankathete ");
        mcQuestion3.setAnswerD(" sin²(α) + cos²(α)");
        mcQuestion3.setCorrespondingLearningGroup(group1);
        mcQuestion3.setCreator(pedro);
        mcQuestion3.setCorrectAnswers("A,C");
        mcQuestionRepository.save(mcQuestion3);

        MCQuestion mcQuestion4 = new MCQuestion();
        mcQuestion4.setQuestion("Zu den natürlichen Zahlen ℕ gehören ..");
        mcQuestion4.setAnswerA("Die Rationalen Zahlen");
        mcQuestion4.setAnswerB("1, 2, 3, 4");
        mcQuestion4.setAnswerC("Die ganzen Zahlen");
        mcQuestion4.setAnswerD("-1, -2, -3, -4");
        mcQuestion4.setCorrespondingLearningGroup(group1);
        mcQuestion4.setCreator(pedro);
        mcQuestion4.setCorrectAnswers("B");
        mcQuestionRepository.save(mcQuestion4);

        // Group 2 - Statistik 1
        MCQuestion mcQuestion5 = new MCQuestion();
        mcQuestion5.setQuestion("Welches Merkmal ist von der Merkmalsart qualitativ?");
        mcQuestion5.setAnswerA("Lebensalter");
        mcQuestion5.setAnswerB("Familienstand");
        mcQuestion5.setAnswerC("Geschlecht");
        mcQuestion5.setAnswerD("Einkommen");
        mcQuestion5.setCorrespondingLearningGroup(group2);
        mcQuestion5.setCreator(maria);
        mcQuestion5.setCorrectAnswers("B,C");
        mcQuestionRepository.save(mcQuestion5);

        MCQuestion mcQuestion6 = new MCQuestion();
        mcQuestion6.setQuestion("Für welches Merkmal eignet sich eine Intervallskala?");
        mcQuestion6.setAnswerA("Geburtsjahr");
        mcQuestion6.setAnswerB("Beruf");
        mcQuestion6.setAnswerC("Einkommen");
        mcQuestion6.setAnswerD("Temperatur in C°");
        mcQuestion6.setCorrespondingLearningGroup(group2);
        mcQuestion6.setCreator(maria);
        mcQuestion6.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion6);

        MCQuestion mcQuestion7 = new MCQuestion();
        mcQuestion7.setQuestion("Welches Merkmal ist komparativ ?");
        mcQuestion7.setAnswerA("Lebensalter");
        mcQuestion7.setAnswerB("Beruf");
        mcQuestion7.setAnswerC("Schulnote");
        mcQuestion7.setAnswerD("Einkommen");
        mcQuestion7.setCorrespondingLearningGroup(group2);
        mcQuestion7.setCreator(maria);
        mcQuestion7.setCorrectAnswers("A,C,D");
        mcQuestionRepository.save(mcQuestion7);

        MCQuestion mcQuestion8 = new MCQuestion();
        mcQuestion8.setQuestion("Bei welchem Merkmal eignet sich eine Ordinalskala ? ");
        mcQuestion8.setAnswerA("Handelsgüteklasse");
        mcQuestion8.setAnswerB("Schulnote");
        mcQuestion8.setAnswerC("Postleitzahl");
        mcQuestion8.setAnswerD("Preis eines Gutes");
        mcQuestion8.setCorrespondingLearningGroup(group1);
        mcQuestion8.setCreator(maria);
        mcQuestion8.setCorrectAnswers("A,B");
        mcQuestionRepository.save(mcQuestion8);

        // Group 3 - Programmierung und Softwareentwicklung
        MCQuestion mcQuestion9 = new MCQuestion();
        mcQuestion9.setQuestion("Welcher Sichtbarkeitsmodifikator hat keine Einschränkung ?");
        mcQuestion9.setAnswerA("public");
        mcQuestion9.setAnswerB("private");
        mcQuestion9.setAnswerC("default");
        mcQuestion9.setAnswerD("protected");
        mcQuestion9.setCorrespondingLearningGroup(group3);
        mcQuestion9.setCreator(omar);
        mcQuestion9.setCorrectAnswers("A");
        mcQuestionRepository.save(mcQuestion9);

        MCQuestion mcQuestion10 = new MCQuestion();
        mcQuestion10.setQuestion("Welcher Datentyp repräsentiert Ganzzahlen ?");
        mcQuestion10.setAnswerA("float");
        mcQuestion10.setAnswerB("double");
        mcQuestion10.setAnswerC("char");
        mcQuestion10.setAnswerD("byte");
        mcQuestion10.setCorrespondingLearningGroup(group3);
        mcQuestion10.setCreator(omar);
        mcQuestion10.setCorrectAnswers("D");
        mcQuestionRepository.save(mcQuestion10);

        MCQuestion mcQuestion11 = new MCQuestion();
        mcQuestion11.setQuestion("Welches UML - Diagramm modelliert einen Ablauf ?");
        mcQuestion11.setAnswerA("Use - Case Diagramm");
        mcQuestion11.setAnswerB("Sequenzdiagramm");
        mcQuestion11.setAnswerC("Kommunikationsdiagramm");
        mcQuestion11.setAnswerD("Interaktionsdiagramm");
        mcQuestion11.setCorrespondingLearningGroup(group3);
        mcQuestion11.setCreator(omar);
        mcQuestion11.setCorrectAnswers("B,C");
        mcQuestionRepository.save(mcQuestion11);

        MCQuestion mcQuestion12 = new MCQuestion();
        mcQuestion12.setQuestion("Welche Funktionseinheit der von-Neumann Rechnerarchitektur repräsentiert die CPU ?");
        mcQuestion12.setAnswerA("Rechenwerk");
        mcQuestion12.setAnswerB("Steuerwerk");
        mcQuestion12.setAnswerC("Speicherwerk");
        mcQuestion12.setAnswerD("Eingabewerk");
        mcQuestion12.setCorrespondingLearningGroup(group3);
        mcQuestion12.setCreator(omar);
        mcQuestion12.setCorrectAnswers("A,B");
        mcQuestionRepository.save(mcQuestion12);

        MCQuestion mcQuestion29 = new MCQuestion();
        mcQuestion29.setQuestion("Welcher Datentyp repräsentiert einen Buchstaben ?");
        mcQuestion29.setAnswerA("boolean");
        mcQuestion29.setAnswerB("int");
        mcQuestion29.setAnswerC("long");
        mcQuestion29.setAnswerD("float");
        mcQuestion29.setCorrespondingLearningGroup(group3);
        mcQuestion29.setCreator(omar);
        mcQuestion29.setCorrectAnswers("");
        mcQuestionRepository.save(mcQuestion29);

        MCQuestion mcQuestion30 = new MCQuestion();
        mcQuestion30.setQuestion("Die Typ-2 Grammatik ist eine .. Grammtik !");
        mcQuestion30.setAnswerA("kontextfreie");
        mcQuestion30.setAnswerB("kontextsensitive");
        mcQuestion30.setAnswerC("reguläre");
        mcQuestion30.setAnswerD("allgemeine");
        mcQuestion30.setCorrespondingLearningGroup(group3);
        mcQuestion30.setCreator(omar);
        mcQuestion30.setCorrectAnswers("A");
        mcQuestionRepository.save(mcQuestion30);

        MCQuestion mcQuestion31 = new MCQuestion();
        mcQuestion31.setQuestion("Ein Byte entspricht wieviel Bits");
        mcQuestion31.setAnswerA("1 Bit");
        mcQuestion31.setAnswerB("2 Bits");
        mcQuestion31.setAnswerC("6 Bits");
        mcQuestion31.setAnswerD("8 Bits");
        mcQuestion31.setCorrespondingLearningGroup(group3);
        mcQuestion31.setCreator(omar);
        mcQuestion31.setCorrectAnswers("D");
        mcQuestionRepository.save(mcQuestion31);

        // Group 4 - Wirtschaftsinformatik
        MCQuestion mcQuestion13 = new MCQuestion();
        mcQuestion13.setQuestion("Die Lehre von Zeichen wird Semiotik genannnt, diese umfasst als Teillehren ..");
        mcQuestion13.setAnswerA("Die Syntaktik");
        mcQuestion13.setAnswerB("Die Semantik");
        mcQuestion13.setAnswerC("Die Pragmatik");
        mcQuestion13.setAnswerD("Die Didaktik");
        mcQuestion13.setCorrespondingLearningGroup(group4);
        mcQuestion13.setCreator(nina);
        mcQuestion13.setCorrectAnswers("A,B,C");
        mcQuestionRepository.save(mcQuestion13);

        MCQuestion mcQuestion14 = new MCQuestion();
        mcQuestion14.setQuestion("Um Entitäten zu verbinden benötigt man ?");
        mcQuestion14.setAnswerA("Ein Attribut !");
        mcQuestion14.setAnswerB("Eine Relation !");
        mcQuestion14.setAnswerC("Ein Schlüssel !");
        mcQuestion14.setAnswerD("Eine Kardinalität !");
        mcQuestion14.setCorrespondingLearningGroup(group4);
        mcQuestion14.setCreator(nina);
        mcQuestion14.setCorrectAnswers("B");
        mcQuestionRepository.save(mcQuestion14);

        MCQuestion mcQuestion15 = new MCQuestion();
        mcQuestion15.setQuestion("Ein Glasfaserkabel ist ..!");
        mcQuestion15.setAnswerA("ein metallischer Leiter");
        mcQuestion15.setAnswerB("leitungsungebunden");
        mcQuestion15.setAnswerC("leitungsgebunden");
        mcQuestion15.setAnswerD("ein nichtmetallischer Leiter");
        mcQuestion15.setCorrespondingLearningGroup(group4);
        mcQuestion15.setCreator(nina);
        mcQuestion15.setCorrectAnswers("C,D");
        mcQuestionRepository.save(mcQuestion15);

        MCQuestion mcQuestion16 = new MCQuestion();
        mcQuestion16.setQuestion("Bei welcher LAN - Topologie haben wir eine hohe Flexibilität ?");
        mcQuestion16.setAnswerA("Bei einer Ring - Topologie.");
        mcQuestion16.setAnswerB("Bei einer Stern - Topolgie.");
        mcQuestion16.setAnswerC("Bei einer Bus - Topolgie.");
        mcQuestion16.setAnswerD("Bei einer Baum - Topolgie.");
        mcQuestion16.setCorrespondingLearningGroup(group4);
        mcQuestion16.setCreator(nina);
        mcQuestion16.setCorrectAnswers("C,D");
        mcQuestionRepository.save(mcQuestion16);

        //Group 5 - BWL and REWE
        MCQuestion mcQuestion17 = new MCQuestion();
        mcQuestion17.setQuestion("Realgüter zählen zu den ... !");
        mcQuestion17.setAnswerA("Freien Gütern");
        mcQuestion17.setAnswerB("Nominalgütern");
        mcQuestion17.setAnswerC("Wirtschaftsgütern");
        mcQuestion17.setAnswerD("Leistungsbündel");
        mcQuestion17.setCorrespondingLearningGroup(group5);
        mcQuestion17.setCreator(hans);
        mcQuestion17.setCorrectAnswers("C");
        mcQuestionRepository.save(mcQuestion17);

        MCQuestion mcQuestion18 = new MCQuestion();
        mcQuestion18.setQuestion("Welche Renten werden durch Knappheit erzielt ?");
        mcQuestion18.setAnswerA("Ricardo - Renten");
        mcQuestion18.setAnswerB("Schumpetere - Renten");
        mcQuestion18.setAnswerC("Quasi - Renten");
        mcQuestion18.setAnswerD("Monopol - Renten");
        mcQuestion18.setCorrespondingLearningGroup(group5);
        mcQuestion18.setCreator(hans);
        mcQuestion18.setCorrectAnswers("A,D");
        mcQuestionRepository.save(mcQuestion18);

        MCQuestion mcQuestion19 = new MCQuestion();
        mcQuestion19.setQuestion("Zu den Elementarfaktoren der Produktion gehören ?");
        mcQuestion19.setAnswerA("Werkstoffe");
        mcQuestion19.setAnswerB("Betriebsmittel");
        mcQuestion19.setAnswerC("Planung");
        mcQuestion19.setAnswerD("Organisation");
        mcQuestion19.setCorrespondingLearningGroup(group5);
        mcQuestion19.setCreator(hans);
        mcQuestion19.setCorrectAnswers("A,B");
        mcQuestionRepository.save(mcQuestion19);

        MCQuestion mcQuestion20 = new MCQuestion();
        mcQuestion20.setQuestion("Die Universität Hohenheim zählt zu welcher Art von Wirtschafssubjekt ?");
        mcQuestion20.setAnswerA("Öffentlicher Betrieb");
        mcQuestion20.setAnswerB("Unternehmen");
        mcQuestion20.setAnswerC("Privater Haushalt");
        mcQuestion20.setAnswerD("Gemischt wirtschaftlicher Betrieb");
        mcQuestion20.setCorrespondingLearningGroup(group5);
        mcQuestion20.setCreator(hans);
        mcQuestion20.setCorrectAnswers("A");
        mcQuestionRepository.save(mcQuestion20);

        MCQuestion mcQuestion36 = new MCQuestion();
        mcQuestion36.setQuestion("Das Recht ein Gut zu gebrauchen, nennt man .. ");
        mcQuestion36.setAnswerA("Usus");
        mcQuestion36.setAnswerB("Usus Fructus");
        mcQuestion36.setAnswerC("Abusus");
        mcQuestion36.setAnswerD("Veräußerungsrecht");
        mcQuestion36.setCorrespondingLearningGroup(group5);
        mcQuestion36.setCreator(hans);
        mcQuestion36.setCorrectAnswers("A");
        mcQuestionRepository.save(mcQuestion36);

        MCQuestion mcQuestion37 = new MCQuestion();
        mcQuestion37.setQuestion("Das Recht sich die Erträge aus der Nutzung eines Gutes anzueignen nennt man .. ");
        mcQuestion37.setAnswerA("Usus");
        mcQuestion37.setAnswerB("Usus Fructus");
        mcQuestion37.setAnswerC("Abusus");
        mcQuestion37.setAnswerD("Veräußerungsrecht");
        mcQuestion37.setCorrespondingLearningGroup(group5);
        mcQuestion37.setCreator(hans);
        mcQuestion37.setCorrectAnswers("B");
        mcQuestionRepository.save(mcQuestion37);

        MCQuestion mcQuestion38 = new MCQuestion();
        mcQuestion38.setQuestion("Auf welchem Ansatz basiert der Market-based View ?");
        mcQuestion38.setAnswerA("5-Forces-Ansatz von Porter");
        mcQuestion38.setAnswerB("Media-Richness-Ansatz von Daft und Lengel");
        mcQuestion38.setAnswerC("Actors-Resources-Activities-Ansatz von Hakansson");
        mcQuestion38.setAnswerD("Structure-follows-Strategy-Ansatz von Chandler");
        mcQuestion38.setCorrespondingLearningGroup(group5);
        mcQuestion38.setCreator(hans);
        mcQuestion38.setCorrectAnswers("A");
        mcQuestionRepository.save(mcQuestion38);


        //Group 6 - Zivilrecht
        MCQuestion mcQuestion21 = new MCQuestion();
        mcQuestion21.setQuestion("Zum Gutachtenstil gehören ?");
        mcQuestion21.setAnswerA("Obersatz");
        mcQuestion21.setAnswerB("Vorraussetzung");
        mcQuestion21.setAnswerC("Subsumtion");
        mcQuestion21.setAnswerD("Ergebnis");
        mcQuestion21.setCorrespondingLearningGroup(group6);
        mcQuestion21.setCreator(max);
        mcQuestion21.setCorrectAnswers("A,B,C,D");
        mcQuestionRepository.save(mcQuestion21);

        MCQuestion mcQuestion22 = new MCQuestion();
        mcQuestion22.setQuestion("Welche Kriterien gehören zum wirskamwerden eines Vertrages ?");
        mcQuestion22.setAnswerA("Zwei übereinstimmende Willenserklärungen");
        mcQuestion22.setAnswerB("Angebot");
        mcQuestion22.setAnswerC("Annahme");
        mcQuestion22.setAnswerD("Konsens");
        mcQuestion22.setCorrespondingLearningGroup(group6);
        mcQuestion22.setCreator(max);
        mcQuestion22.setCorrectAnswers("A,B,C,D");
        mcQuestionRepository.save(mcQuestion22);

        MCQuestion mcQuestion23 = new MCQuestion();
        mcQuestion23.setQuestion("Welche Beendigungsgründe von Verträgen gibt es ?");
        mcQuestion23.setAnswerA("Wiederruf");
        mcQuestion23.setAnswerB("Konfusion");
        mcQuestion23.setAnswerC("Aufhebung");
        mcQuestion23.setAnswerD("Schuldersetzung");
        mcQuestion23.setCorrespondingLearningGroup(group6);
        mcQuestion23.setCreator(max);
        mcQuestion23.setCorrectAnswers("A,B,C,D");
        mcQuestionRepository.save(mcQuestion23);

        MCQuestion mcQuestion24 = new MCQuestion();
        mcQuestion24.setQuestion("Ab wann ist laut BGB §1 der Beginn der Rechtsfähigkeit ?");
        mcQuestion24.setAnswerA("Ab der Vollendung der Geburt");
        mcQuestion24.setAnswerB("Ab dem 18. Lebensjahr");
        mcQuestion24.setAnswerC("Ab dem 16. Lebensjahr");
        mcQuestion24.setAnswerD("Ab Kidnesalter");
        mcQuestion24.setCorrespondingLearningGroup(group6);
        mcQuestion24.setCreator(max);
        mcQuestion24.setCorrectAnswers("A");
        mcQuestionRepository.save(mcQuestion24);

        // Group 7 - DSA
        MCQuestion mcQuestion25 = new MCQuestion();
        mcQuestion25.setQuestion("Welche Komplexitätsklasse wächst am schnellsten ?");
        mcQuestion25.setAnswerA("O( n³ )");
        mcQuestion25.setAnswerB("O( n )");
        mcQuestion25.setAnswerC("O( log n )");
        mcQuestion25.setAnswerD("O( n! )");
        mcQuestion25.setCorrespondingLearningGroup(group7);
        mcQuestion25.setCreator(leon);
        mcQuestion25.setCorrectAnswers("D");
        mcQuestionRepository.save(mcQuestion25);

        MCQuestion mcQuestion26 = new MCQuestion();
        mcQuestion26.setQuestion("Wie wird das Sortieren durch Aufsteigen genannt ?");
        mcQuestion26.setAnswerA("InsertionSort");
        mcQuestion26.setAnswerB("BubbleSort");
        mcQuestion26.setAnswerC("QuickSort");
        mcQuestion26.setAnswerD("MergeSort");
        mcQuestion26.setCorrespondingLearningGroup(group7);
        mcQuestion26.setCreator(leon);
        mcQuestion26.setCorrectAnswers("B");
        mcQuestionRepository.save(mcQuestion26);

        MCQuestion mcQuestion27 = new MCQuestion();
        mcQuestion27.setQuestion("Welche Traversierung entspricht der Breitensuche ?");
        mcQuestion27.setAnswerA("Inorder");
        mcQuestion27.setAnswerB("Preorder");
        mcQuestion27.setAnswerC("Postorder");
        mcQuestion27.setAnswerD("Levelorder");
        mcQuestion27.setCorrespondingLearningGroup(group7);
        mcQuestion27.setCreator(leon);
        mcQuestion27.setCorrectAnswers("D");
        mcQuestionRepository.save(mcQuestion27);

        MCQuestion mcQuestion28 = new MCQuestion();
        mcQuestion28.setQuestion("Bei welchem Baum kann der Knoten mehrere Schlüssel enthalten ?");
        mcQuestion28.setAnswerA("B-Baum");
        mcQuestion28.setAnswerB("2-3-4 Baum");
        mcQuestion28.setAnswerC("Binärbaum");
        mcQuestion28.setAnswerD("Patricia-Baum");
        mcQuestion28.setCorrespondingLearningGroup(group7);
        mcQuestion28.setCreator(leon);
        mcQuestion28.setCorrectAnswers("A,B");
        mcQuestionRepository.save(mcQuestion28);

        //Group 8 - SOPRA

        MCQuestion mcQuestion41 = new MCQuestion();
        mcQuestion41.setQuestion("Welcher Link wird korrekt ausgeführt ? ");
        mcQuestion41.setAnswerA("th:href=\"${welcome(id=${id)}\" ");
        mcQuestion41.setAnswerB("th:href=\"@{welcome(id=${id)}\" ");
        mcQuestion41.setAnswerC("th:href=\"${welcome(id=*{id)}\" ");
        mcQuestion41.setAnswerD("th:href=\"@{welcome(id=#{id)}\" ");
        mcQuestion41.setCorrespondingLearningGroup(group8);
        mcQuestion41.setCreator(erhan);
        mcQuestion41.setCorrectAnswers("B");
        mcQuestionRepository.save(mcQuestion41);

        MCQuestion mcQuestion40 = new MCQuestion();
        mcQuestion40.setQuestion("Wie kennzeichnet man Kommentare in der Style-Sheet Datei (CSS) ? ");
        mcQuestion40.setAnswerA("<!-- hier steht ein Kommentar -->");
        mcQuestion40.setAnswerB("-// hier steht ein Kommentar //-");
        mcQuestion40.setAnswerC("/* hier steht ein Kommentar */");
        mcQuestion40.setAnswerD("*** hier steht ein Kommentar ***");
        mcQuestion40.setCorrespondingLearningGroup(group8);
        mcQuestion40.setCreator(erhan);
        mcQuestion40.setCorrectAnswers("C");
        mcQuestionRepository.save(mcQuestion40);

        MCQuestion mcQuestion32 = new MCQuestion();
        mcQuestion32.setQuestion("Mit welchem Befehl aktualisiert man das lokale Repository in Git ?");
        mcQuestion32.setAnswerA("git pull");
        mcQuestion32.setAnswerB("git add");
        mcQuestion32.setAnswerC("git diff");
        mcQuestion32.setAnswerD("git merge");
        mcQuestion32.setCorrespondingLearningGroup(group8);
        mcQuestion32.setCreator(erhan);
        mcQuestion32.setCorrectAnswers("A");
        mcQuestionRepository.save(mcQuestion32);

        MCQuestion mcQuestion33 = new MCQuestion();
        mcQuestion33.setQuestion("Welche Phasen gibt es im Software-Engineering ?");
        mcQuestion33.setAnswerA("Planung");
        mcQuestion33.setAnswerB("Wartung");
        mcQuestion33.setAnswerC("Abbruch");
        mcQuestion33.setAnswerD("Insatallation");
        mcQuestion33.setCorrespondingLearningGroup(group8);
        mcQuestion33.setCreator(erhan);
        mcQuestion33.setCorrectAnswers("A,B,D");
        mcQuestionRepository.save(mcQuestion33);

        MCQuestion mcQuestion34 = new MCQuestion();
        mcQuestion34.setQuestion("Was veranschaulicht ein Use-Case Diagramm ?");
        mcQuestion34.setAnswerA("Einen zeitlichen Ablauf");
        mcQuestion34.setAnswerB("Einen Anwendungsfall");
        mcQuestion34.setAnswerC("Einen Zustand");
        mcQuestion34.setAnswerD("Eine Interaktion");
        mcQuestion34.setCorrespondingLearningGroup(group8);
        mcQuestion34.setCreator(erhan);
        mcQuestion34.setCorrectAnswers("B");
        mcQuestionRepository.save(mcQuestion34);

        MCQuestion mcQuestion35 = new MCQuestion();
        mcQuestion35.setQuestion("Eine Beziehung zwischen zwei oder mehreren Klassen bezeichnet man als.. ");
        mcQuestion35.setAnswerA("Generalisierung");
        mcQuestion35.setAnswerB("Spezialisierung");
        mcQuestion35.setAnswerC("Assoziation");
        mcQuestion35.setAnswerD("Komposition");
        mcQuestion35.setCorrespondingLearningGroup(group8);
        mcQuestion35.setCreator(erhan);
        mcQuestion35.setCorrectAnswers("C");
        mcQuestionRepository.save(mcQuestion35);

        MCQuestion mcQuestion39 = new MCQuestion();
        mcQuestion39.setQuestion("Mit welchem Befehl bestätigt man seine Änderungen in Git ? ");
        mcQuestion39.setAnswerA("git add *");
        mcQuestion39.setAnswerB("git commit -m");
        mcQuestion39.setAnswerC("git push");
        mcQuestion39.setAnswerD("git commit -b");
        mcQuestion39.setCorrespondingLearningGroup(group8);
        mcQuestion39.setCreator(erhan);
        mcQuestion39.setCorrectAnswers("B");
        mcQuestionRepository.save(mcQuestion39);







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
        groupPost3.setText("Habt Ihr Fragen zu PSE - Dann stellt Sie hier!");
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

        com.desj.model.GroupPost groupPost11 = new com.desj.model.GroupPost();
        groupPost11.setAssociatedUser(julien);
        groupPost11.setText("Hallo zusammen, für Fragen bezüglich Sopra oder dem Quiz nutzt bitte dieses Forum.");
        groupPost11.setAssociatedLearningGroup(group8);
        groupPostRepository.save(groupPost11);


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

        Question question1 = new Question();
        question1.setQuestion("Was ist die Definition von Entwurf");
        question1.setAnswer("Entwurf ist eine Aktivitaet, bei der die technishe Loesungsstruktur fuer ein System entwickelt wird.");
        question1.setCreator(desi);
        question1.setCorrespondingLearningGroup(group7);
        List<Question> create = new ArrayList<>();
        create.add(question1);
        desi.setCreatedQuestions(create);
        questionReposiory.save(question1);

    }
}
