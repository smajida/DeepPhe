package org.apache.ctakes.core.cc;

import org.apache.ctakes.cancer.ae.XMISerializer;
import org.apache.ctakes.core.util.DocumentIDAnnotationUtil;
import org.apache.ctakes.typesystem.type.structured.DocumentID;
import org.apache.ctakes.typesystem.type.structured.DocumentIdPrefix;
import org.apache.log4j.Logger;
import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.collection.CasConsumerDescription;
import org.apache.uima.fit.component.CasConsumer_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.URL;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/12/2016
 */
public class FileTreeXmiWriter extends CasConsumer_ImplBase {

   static private final Logger LOGGER = Logger.getLogger( "FileTreeXmiWriter" );

   /**
    * Name of configuration parameter that must be set to the path of a directory into which the
    * output files will be written.
    */
   public static final String PARAM_OUTPUTDIR = "OutputDirectory";
   @ConfigurationParameter( name = PARAM_OUTPUTDIR,
         description = "Root output directory to write xmi files",
         mandatory = true )
   private File _outputRootDir;


   /**
    * {@inheritDoc}
    */
   @Override
   public void initialize( final UimaContext context ) throws ResourceInitializationException {
      super.initialize( context );
      if ( !_outputRootDir.exists() ) {
         _outputRootDir.mkdirs();
      }
   }

   /**
    * Write the cas into an xmi output file named based upon the document id and located based upon the document id prefix.
    * {@inheritDoc}
    */
   @Override
   public void process( final CAS cas ) throws AnalysisEngineProcessException {
      JCas jcas;
      try {
         jcas = cas.getJCas();
      } catch ( CASException casE ) {
         throw new AnalysisEngineProcessException( casE );
      }
      final String fileName = DocumentIDAnnotationUtil.getDocumentIdForFile( jcas ) + ".xmi";

      String subDirectory = "";
      JFSIndexRepository indexes = jcas.getJFSIndexRepository();
      FSIterator idPrefixIterator = indexes.getAllIndexedFS( DocumentIdPrefix.type );
      if ( idPrefixIterator != null && idPrefixIterator.hasNext() ) {
         final DocumentIdPrefix idPrefix = (DocumentIdPrefix)idPrefixIterator.next();
         if ( idPrefix != null ) {
            try {
               subDirectory = idPrefix.getDocumentIdPrefix();
            } catch ( CASRuntimeException var5 ) {
               LOGGER.debug( "No subdirectory information for " + fileName );
               subDirectory = "";
            }
         }
      }
      if ( !subDirectory.isEmpty() ) {
         new File( _outputRootDir + "/" + subDirectory ).mkdirs();
         subDirectory = "/" + subDirectory;
      }
      final File xmiFile = new File( _outputRootDir + subDirectory, fileName );
      try {
         writeXmi( jcas.getCas(), xmiFile );
      } catch ( IOException | SAXException multE ) {
         throw new AnalysisEngineProcessException( multE );
      }
   }

   /**
    * Serialize a CAS to a file in XMI format
    *
    * @param cas  CAS to serialize
    * @param file output file
    * @throws IOException  -
    * @throws SAXException -
    */
   public static void writeXmi( final CAS cas, final File file ) throws IOException, SAXException {
      try ( OutputStream outputStream = new BufferedOutputStream( new FileOutputStream( file ) ) ) {
         XmiCasSerializer casSerializer = new XmiCasSerializer( cas.getTypeSystem() );
         XMISerializer xmiSerializer = new XMISerializer( outputStream, true );
         casSerializer.serialize( cas, xmiSerializer.getContentHandler() );
      }
   }


   /**
    * Parses and returns the descriptor for this collection reader. The descriptor is stored in the
    * uima.jar file and located using the ClassLoader.
    *
    * @return an object containing all of the information parsed from the descriptor.
    * @throws InvalidXMLException if the descriptor is invalid or missing
    */
   public static CasConsumerDescription getDescription() throws InvalidXMLException {
      final InputStream descStream = FileTreeXmiWriter.class.getResourceAsStream( "FileTreeXmiWriter.xml" );
      return UIMAFramework.getXMLParser().parseCasConsumerDescription(
            new XMLInputSource( descStream, null ) );
   }

   public static URL getDescriptorURL() {
      return FileTreeXmiWriter.class.getResource( "FileTreeXmiWriter.xml" );
   }

}
