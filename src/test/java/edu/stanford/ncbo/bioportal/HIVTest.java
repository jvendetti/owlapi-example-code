package edu.stanford.ncbo.bioportal;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OBODocumentFormat;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.model.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class HIVTest {

    @Test
    public void loadHIV() throws Exception {

        // Can this ontology be loaded w/out errors?
        File file = new File("src/test/resources/HIVOntology2.7.obo");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);

        // Ontology IRI and version info
        OWLOntologyID ontID = ontology.getOntologyID();
        System.out.println("Ontology ID: " + ontID.getOntologyIRI());
        System.out.println("Ontology version: " + ontID.getVersionIRI());

        // What's the axiom count?
        System.out.println("Ontology axiom count: " + ontology.getAxiomCount());

        // What's the document format?
        OWLDocumentFormat format = manager.getOntologyFormat(ontology);
        System.out.println("Ontology document format: " + format);

        // Are there any imports?
        Set<OWLOntology> ontologyImports = new HashSet<OWLOntology>(manager.getImports(ontology));
        if (ontologyImports.isEmpty()) {
            System.out.println("Ontology has no imports");
        } else {
            ontologyImports.forEach(ontologyImport -> {
                System.out.println("Imported ontology ID: " + ontologyImport.getOntologyID());
            });
        }

        // Are there any direct imports?
        Set<OWLOntology> directImports = new HashSet<OWLOntology>(manager.getDirectImports(ontology));
        if (directImports.isEmpty()) {
            System.out.println("Ontology has no direct imports");
        } else {
            directImports.forEach(directImport -> {
                System.out.println("Directly imported ontology ID: " + directImport.getOntologyID());
            });
        }

        // 'antiretroviral drug' - is this a class?  Is it anonymous?
        IRI owlClassIRI = IRI.create("http://purl.obolibrary.org/obo/HIV_4");
        OWLClass owlClass = manager.getOWLDataFactory().getOWLClass(owlClassIRI);
        System.out.println(owlClass.isOWLClass());
        System.out.println(owlClass.isAnonymous());

        // Is 'antiretroviral drug' in the ontology signature?
        Set<OWLClass> classes = new HashSet<OWLClass>(ontology.getClassesInSignature());
        classes.forEach(cls -> {
            IRI clsIRI = cls.getIRI();
            if (clsIRI.toString().equals("http://purl.obolibrary.org/obo/HIV_4")) {
                System.out.println("I found the class you're looking for...");
            }
        });
    }

    @Test
    public void loadHIV_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/HIVOntology2.7.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);

        // Ontology IRI and version info
        OWLOntologyID ontID = ontology.getOntologyID();
        System.out.println("Ontology ID: " + ontID.getOntologyIRI());
        System.out.println("Ontology version: " + ontID.getVersionIRI());

        // TODO: Why is the version always absent?  It's properly declared in the OBO file.
    }
}
