package edu.stanford.ncbo.bioportal;

import org.junit.Ignore;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.NTriplesDocumentFormat;
import org.semanticweb.owlapi.formats.OBODocumentFormat;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.formats.TurtleDocumentFormat;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.SimpleRootClassChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class SimpleLoadTest {

    static final Logger LOG = LoggerFactory.getLogger(SimpleLoadTest.class);

    @Ignore("Missing co-ode import results in UnloadableImportException")
    @Test
    public void testLoadBasicVertebrate() throws Exception {
        File file = new File("src/test/resources/basic-vertebrate-gross-anatomy_v1.1.owl");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
    }

    @Test
    public void testLoadPSIMSOntology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/psi-ms.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoadBPFORMSOntology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/bpforms.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("BPFORMS", ontology);
    }

    @Test
    public void testLoadHoomOrphanetOntology() throws Exception {
        String path = "src/test/resources/hoom_orphanet.owl";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path));

        OWLOntologyLoaderConfiguration config = new OWLOntologyLoaderConfiguration();
        config.setStrict(true);
        config.setBannedParsers("org.semanticweb.owlapi.rio.RioTrixParserFactory");

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource, config);
        assertNotNull(ontology);
        System.out.println("Ontology format: " + manager.getOntologyFormat(ontology));

        OWLDataFactory dataFactory = manager.getOWLDataFactory();
        IRI iri = IRI.create("http://www.semanticweb.org/ontology/HOOM#20574610(PMID]");
        OWLClass owlClass = dataFactory.getOWLClass(iri);
        assertNotNull(owlClass);
    }

    @Test
    public void testLoadHIVOntology26_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/HIVontology2.6.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoadHIVOntology27_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/HIVOntology2.7.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoadGMMOntology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/gomapman-ontology.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoadHIVOntology_WithoutDocumentFormat() throws Exception {
        File file = new File("src/test/resources/HIVOntology2.7.obo");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        //OWLDocumentFormat format = manager.getOntologyFormat(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
        System.out.println(manager.getOntologyFormat(ontology));
    }

    @Test
    public void testLoadProteinOntology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/pro_reasoned_v48_original.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoadProteinOntology_WithoutDocumentFormat() throws Exception {
        File file = new File("src/test/resources/pro_reasoned.obo");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testloadPHENXOntology() throws Exception {
        String path = "./src/test/resources/phenXOnto_0316.owl";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new RDFXMLDocumentFormat());

        OWLOntologyLoaderConfiguration configuration = new OWLOntologyLoaderConfiguration();
        configuration.setStrict(true);

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource, configuration);
        assertNotNull(ontology);

        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoadAATOntology() throws Exception {
        String path = "./src/test/resources/aat/GETTY_Many_Triples_0900.nt";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new TurtleDocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
    }

    @Test
    public void testLoadDOIDOntology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/doid.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoadORDOOntology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/ordo_orphanet.owl";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new RDFXMLDocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoadORDOOntology_WithoutDocumentFormat() throws Exception {
        File file = new File("src/test/resources/ordo_orphanet.owl");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        LOG.info("orphanet ontology IRI: {}", ontology.getOntologyID().getOntologyIRI());
        LOG.info("orphanet version: {}", ontology.getOntologyID().getVersionIRI());
        LOG.info("orphanet format: {}", manager.getOntologyFormat(ontology));
        LOG.info("orphanet axiom count: {}", ontology.getAxiomCount());
    }

    @Test
    public void testLoadCHEBIOntology() throws Exception {
        File file = new File("src/test/resources/chebi.obo");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        LOG.info("CHEBI ontology ID: {}", ontology.getOntologyID());
        LOG.info("CHEBI axiom count: {}", ontology.getAxiomCount());
    }

    @Test
    public void testLoadAAOOntology() throws Exception {
        String path = "src/test/resources/AAO_v3.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        LOG.info("AAO ontology ID: {}", ontology.getOntologyID());
        LOG.info("AAO version: {}", ontology.getOntologyID().getVersionIRI());
        LOG.info("AAO axiom count: {}", ontology.getAxiomCount());
    }

    @Test
    public void testLoadATMOntology() throws Exception {
        String path = "src/test/resources/african_medicine.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        LOG.info("ATMO ontology ID: {}", ontology.getOntologyID());
        LOG.info("ATMO version: {}", ontology.getOntologyID().getVersionIRI());
        LOG.info("ATMO axiom count: {}", ontology.getAxiomCount());
    }

    @Test
    public void testLoadPLATSTGOntology() throws Exception {
        String path = "src/test/resources/Pdu_Stages.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("PLATSTG", ontology);
    }

    @Test
    public void testLoadBTOOntology() throws Exception {
        String path = "src/test/resources/BrendaTissue.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("BTO", ontology);
    }

    @Test
    public void testLoadLPTOntology() throws Exception {
        // String path = "src/test/resources/lpt/LPT_v2.2";
        String path = "src/test/resources/lpt/LPT_v3.1";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("LPT", ontology);
    }

    @Test
    public void testLoadCIINTEADOOntology() throws Exception {
        String path = "src/test/resources/ciinteado.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        LOG.info("CIINTEADO ontology ID: {}", ontology.getOntologyID());
        LOG.info("CIINTEADO version: {}", ontology.getOntologyID().getVersionIRI());
        LOG.info("CIINTEADO axiom count: {}", ontology.getAxiomCount());
    }

    @Test
    public void testLoadCISAVIADOOntology() throws Exception {
        String path = "src/test/resources/cisaviado.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        LOG.info("CISAVIADO ontology ID: {}", ontology.getOntologyID());
        LOG.info("CISAVIADO version: {}", ontology.getOntologyID().getVersionIRI());
        LOG.info("CISAVIADO axiom count: {}", ontology.getAxiomCount());
    }

    @Test
    public void testLoadCCOOntology() throws Exception {
//        File file = new File("src/test/resources/cco.rdf");
//        IRI iri = IRI.create(file);
//        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
//        OWLOntology ontology = manager.loadOntology(iri);

        String path = "src/test/resources/cco.rdf";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path));

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

        OWLOntologyLoaderConfiguration conf = new OWLOntologyLoaderConfiguration();
        conf = conf.setMissingImportHandlingStrategy(MissingImportHandlingStrategy.SILENT);

        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource, conf);

        assertNotNull(ontology);
        LOG.info("CCO ontology ID: {}", ontology.getOntologyID());
        LOG.info("CCO axiom count: {}", ontology.getAxiomCount());
    }

    @Test
    public void testLoadEFOOntology() throws Exception {
        File file = new File("src/test/resources/EFO_inferred.owl");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        LOG.info("EFO ontology ID: {}", ontology.getOntologyID());
        LOG.info("EFO axiom count: {}", ontology.getAxiomCount());
    }

    @Test
    public void testLoadONTONEOOntology() throws Exception {
        File file = new File("src/test/resources/ontoneo.owl");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        logOntologyBasics("ONTONEO", ontology);
    }

    @Test
    public void testLoadENVOOntology() throws Exception {
        String path = "src/test/resources/envo-basic.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        LOG.info("ENVO ontology ID: {}", ontology.getOntologyID());
        LOG.info("ENVO version: {}", ontology.getOntologyID().getVersionIRI());
        LOG.info("ENVO axiom count: {}", ontology.getAxiomCount());
    }

    @Test
    public void testLoadHAAURAADOOntology() throws Exception {
        String path = "src/test/resources/haauraado.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("HAAURAADO", ontology);
    }

    @Test
    public void testLoadHAROREADOOntology() throws Exception {
        String path = "src/test/resources/haroreado.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("HAROREADO", ontology);
    }

    @Test
    public void testLoadIMMDISOntology() throws Exception {
        String path = "src/test/resources/DIS_OBO_update.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("IMMDIS", ontology);
    }

    @Test
    public void testLoadPHMAMMADOOntology() throws Exception {
        String path = "src/test/resources/phmammado.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("PHMAMMADO", ontology);
    }

    @Test
    public void testLoadPHFUMIADOOntology() throws Exception {
        String path = "src/test/resources/phfumiado.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("PHFUMIADO", ontology);
    }

    @Test
    public void testLoadMOOCCUADOOntology() throws Exception {
        String path = "src/test/resources/mooccuado.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("MOOCCUADO", ontology);
    }

    @Test
    public void testLoadMOOCCIADOOntology() throws Exception {
        String path = "src/test/resources/moocciado.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("MOOCCIADO", ontology);
    }

    @Test
    public void testLoadMOOCULADOOntology() throws Exception {
        String path = "src/test/resources/mooculado.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("MOOCULADO", ontology);
    }

    @Test
    public void testLoadRNAOOntology() throws Exception {
        String path = "src/test/resources/rnao.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("RNAO", ontology);
    }

    @Test
    public void testLoadMSOntology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/psi-ms.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("MS", ontology);
    }

    @Test
    public void testLoadCMOOntology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/clinical_measurement.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("CMO", ontology);
    }

    @Test
    public void testLoadCLOntology() throws Exception {
        String path = "src/test/resources/cl.owl";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new RDFXMLDocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);

        Set<OWLOntology> ontologies = new HashSet<>(manager.getOntologies());
        SimpleRootClassChecker rootClassChecker = new SimpleRootClassChecker(ontologies);

        IRI iri = IRI.create("http://purl.obolibrary.org/obo/BFO_0000004");
        OWLDataFactory factory = manager.getOWLDataFactory();
        OWLClass bfoClass = factory.getOWLClass(iri);

        boolean isRoot = rootClassChecker.isRootClass(bfoClass);
        LOG.info("Class with IRI {} is root: {}", iri.toString(), isRoot);
    }

    @Test
    public void testLoadABDOntology() throws Exception {
        File file = new File("src/test/resources/disease.owlrdf.xml");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        LOG.info("ABD ontology ID: {}", ontology.getOntologyID());
        LOG.info("ABD axiom count: {}", ontology.getAxiomCount());
    }

    @Test
    public void testLoadSWOOntology() throws Exception {
        File file = new File("src/test/resources/swo_merged.owl");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        LOG.info("SWO ontology ID: {}", ontology.getOntologyID());
        LOG.info("SWO axiom count: {}", ontology.getAxiomCount());
    }

    @Test
    public void testLoadRETOOntology() throws Exception {
        String path = "src/test/resources/reto.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("RETO", ontology);
    }

    @Test
    public void testLoadSEPOOntology() throws Exception {
        String path = "/Users/jvendetti/Downloads/sep.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("SEP", ontology);
    }

    @Test
    public void testLoad_ONL_MSA_Ontology() throws Exception {
        File file = new File("src/test/resources/ontoneurolog-mental-state-assessment.owl");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        logOntologyBasics("ONL-MSA", ontology);
    }

    @Test
    public void testLoad_IGTO_Ontology() throws Exception {
        File file = new File("src/test/resources/iGTO.owl");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        logOntologyBasics("IGTO", ontology);
    }

    @Test
    public void testLoad_PXO_Ontology() throws Exception {
        File file = new File("src/test/resources/PxOmetazoa.owl");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        logOntologyBasics("PXO", ontology);
    }

    @Test
    public void testLoad_PLOSTHES_Ontology() throws Exception {
        File file = new File("src/test/resources/PLOS_thesaurus_SKOS_ver_2016-4_noEdNotes.xml");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        logOntologyBasics("PLOSTHES", ontology);
    }

    @Test
    public void testLoad_NEO_Ontology() throws Exception {
        File file = new File("src/test/resources/NEO.owl");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);

        OWLOntologyLoaderConfiguration conf = new OWLOntologyLoaderConfiguration();
        conf = conf.setMissingImportHandlingStrategy(MissingImportHandlingStrategy.THROW_EXCEPTION);

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource, conf);

        assertNotNull(ontology);
        logOntologyBasics("NEO", ontology);
    }

    @Test
    public void testLoadSWEETOntology() throws Exception {
        File file = new File("/Users/jvendetti/Development/GitHub/sweet/src/sweetAll.ttl");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("SWEET", ontology);
    }

    @Test
    public void testLoadCNOOntology() throws Exception {
        OWLOntologyLoaderConfiguration conf = new OWLOntologyLoaderConfiguration();
        conf = conf.setMissingImportHandlingStrategy(MissingImportHandlingStrategy.SILENT);

        File file = new File("src/test/resources/cno/cnov0_5.owl");
        File rootDir = new File("src/test/resources/cno");
        AutoIRIMapper mapper = new AutoIRIMapper(rootDir, true);

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        manager.getIRIMappers().add(mapper);
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource, conf);

        assertNotNull(ontology);
        logOntologyBasics("CNO", ontology);
    }

    @Test
    public void testLoadIDODENOntology() throws Exception {
        File file = new File("src/test/resources/idoden_beta0.15b.owl");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("IDODEN", ontology);
    }

    @Test
    public void testLoadSBO() throws Exception {
        File file = new File("src/test/resources/SBO_OBO.obo");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("SBO", ontology);
    }

    @Test
    public void testLoad_CIDIT_Ontology() throws Exception {
        File file = new File("src/test/resources/CIDIT_V1.2.owl");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("CIDIT_V1_2", ontology);
    }

    @Test
    public void testLoad_AGROVOC_Ontology() throws Exception {
        File file = new File("src/test/resources/agrovoc_2021-12-02_lod.nt");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("AGROVOC", ontology);
    }

    @Test
    public void testLoad_AGROVOCNQ_Ontology() throws Exception {
        File file = new File("src/test/resources/agrovoc_2021-11-03_lod.nq");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        logOntologyBasics("AGROVOCNQ", ontology);
    }

    @Test
    public void testLoad_WoodyPlantOntology_Submission58() throws Exception {
        File file = new File("src/test/resources/co_357.rdf");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoad_CST_Ontology() throws Exception {
        File file = new File("src/test/resources/cancer_staging_terms.owl");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoad_MOD_Ontology() throws Exception {
        File file = new File("src/test/resources/MOD/mod-v2.0_ontology.owl");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoad_MODTTL_Ontology() throws Exception {
        String path = "src/test/resources/MOD/mod-v2.0_profile.ttl";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new TurtleDocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoad_MOD_Ontology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/MOD/mod-v2.0_ontology.owl";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new RDFXMLDocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoad_BIBFRAME_Ontology() throws Exception {
        File file = new File("src/test/resources/bibframe.rdf");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoad_WoodyPlantOntology_Submission58_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/co_357_v2.rdf";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new RDFXMLDocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoad_CIDIT_Ontology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/CIDIT_V1.2.owl";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new RDFXMLDocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoad_AIO_Ontology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/aio-full.owl";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new RDFXMLDocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoad_BRO_v3_2_Ontology_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/BRO_v3.2.owl";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new RDFXMLDocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void testLoadSKOSCoreVocabulary() throws Exception {
        for (int i=0; i<10; i++) {
            IRI skosCoreIRI = IRI.create("http://www.w3.org/2004/02/skos/core");
            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
            OWLOntology ontology = manager.loadOntology(skosCoreIRI);
            assertNotNull(ontology);
        }
    }

    @Test
    public void testLoadMODSCIOntology() throws Exception {
//        File file = new File("/Users/jvendetti/Development/Examples/ontologies/modsci/ModSci.owl");
//        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
//        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
//        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
//        assertNotNull(ontology);
//        logOntologyBasics("MODSCI", ontology);

        // Try to serialize ModSci.owl to N-Triples format
//        File nTriplesFile = new File("/Users/jvendetti/Development/Examples/ontologies/modsci/ModSci.triples");
//        IRI iri = IRI.create(nTriplesFile);
//        manager.saveOntology(ontology, new TurtleDocumentFormat(), iri);

        // Try to serialize owlapi.xrdf to Turtle format
//        File xrdfFile = new File("/Users/jvendetti/Development/Examples/ontologies/modsci/owlapi.xrdf");
//        OWLOntologyManager manager1 = OWLManager.createOWLOntologyManager();
//        OWLOntology ontology1 = manager1.loadOntologyFromOntologyDocument(xrdfFile);
//        File nTriplesFile1 = new File("/Users/jvendetti/Development/Examples/ontologies/modsci/owlapi.xrdf.triples");
//        manager1.saveOntology(ontology1, new TurtleDocumentFormat(), IRI.create(nTriplesFile1));

        // Try to serialize owlapi.xrdf to N-Triples format
        File xrdfFile = new File("/Users/jvendetti/Development/Examples/ontologies/modsci/owlapi.xrdf");
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(xrdfFile);
        File nTriplesFile = new File("/Users/jvendetti/Development/Examples/ontologies/modsci/owlapi.ntriples");
        manager.saveOntology(ontology, new NTriplesDocumentFormat(), IRI.create(nTriplesFile));
    }

    @Test
    public void testLoadOBOEOntologyFromURL() throws Exception {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        IRI ontologyIRI = IRI.create("http://ecoinformatics.org/oboe/oboe.1.2/oboe-core.owl");
        OWLOntology ontology = manager.loadOntology(ontologyIRI);
        System.out.println("Axiom count: " + ontology.getAxiomCount());
    }

    private void logOntologyBasics(String acronym, OWLOntology ontology) {
        LOG.info("{} ontology ID: {}", acronym, ontology.getOntologyID());
        LOG.info("{} version: {}", acronym, ontology.getOntologyID().getVersionIRI());
        LOG.info("{} axiom count: {}", acronym, ontology.getAxiomCount());
    }

}