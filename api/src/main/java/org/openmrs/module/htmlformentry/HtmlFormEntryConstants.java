package org.openmrs.module.htmlformentry;

/** Constants used by the HTML Form Entry module */

public class HtmlFormEntryConstants {

	/** Constant used by {@see HtmlFormEntryUtil#documentToString(Document)} */
    public final static String CONSTANT_XML = "xml";
    
    /** Constant used by {@see HtmlFormEntryUtil#documentToString(Document)} */
    public final static String CONSTANT_YES = "yes";
    
    /** Constant used by {@see HtmlFormEntryUtil#documentToString(Document)} */
    public final static String ERROR_TRANSFORMER_1 = "TransformerFactory.newTransformer error:";
   
    /** Constant used by {@see HtmlFormEntryUtil#documentToString(Document)} */
    public final static String ERROR_TRANSFORMER_2 = "Transformer.transform error:";
    
    public final static String[] ENCOUNTER_TAGS = {"encounterDate", "encounterLocation", "encounterProvider"};
    
    public final static String[] PATIENT_TAGS = {"patient"};

    public final static String GP_DATE_FORMAT = "htmlformentry.dateFormat";
    
    public final static String GP_SHOW_DATE_FORMAT = "htmlformentry.showDateFormat";

    public final static String GP_CLASSES_NOT_TO_EXPORT_WITH_MDS = "htmlformentry.classesNotToExportWithMetadataSharing";
    
    public static final String COMPLEX_UUID = "8d4a6242-c2cc-11de-8d13-0010c6dffd0f";

    public static final String ANSWER_LOCATION_TAGS="answerLocationTags";
    
    public static final String MODULE_ID = "htmlformentry";

    // Global property names
 	public static final String PROP_PDF_PAGE_SIZE = MODULE_ID + ".pdfPageSize";
 	public static final String PROP_PDF_ENABLED = MODULE_ID + ".pdfEnabled";
 	public static final String PROP_WKHTMLTOPDF_PATH = MODULE_ID + ".wkhtmltopdfPath";
 	public static final String PROP_WKHTMLTOPDF_ARGS = MODULE_ID + ".wkhtmltopdfArgs";
}
