package com.geektrust.familytree;

import com.geektrust.familytree.domain.EDescendant;
import com.geektrust.familytree.domain.EPartner;
import com.geektrust.familytree.exception.NoSuchRelationshipException;
import com.geektrust.familytree.initialize.Initializer;
import com.geektrust.familytree.parse.InputParser;
import com.geektrust.familytree.repository.DescendantRepository;
import com.geektrust.familytree.repository.PartnerRepository;
import com.geektrust.familytree.repository.RelativesRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //initialize the repositories
        RelativesRepository<EDescendant, String> descendantsRepository = new DescendantRepository(new HashMap<>());
        RelativesRepository<EPartner, String> partnersRepository = new PartnerRepository(new HashMap<>());

        //pass the repositories to our initializer
        Initializer initializer = new Initializer(partnersRepository, descendantsRepository);

        //initialize the parser
        InputParser inputParser = new InputParser(initializer.init(), descendantsRepository, partnersRepository);

        //get the file location and start execution
        String inputFileLocation = args[0];
        BufferedReader br = new BufferedReader(new FileReader(new File(inputFileLocation)));
        Scanner scanner = new Scanner(br);
        while(scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            inputParser.execute(commands);
        }
    }
}
