package org.apache.ctakes.cancer.tnm;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 8/20/2015
 */
enum TnmClassPrefixType {
   C( "Classification given by clinical examination", 'c' ),
   P( "Classification given by pathologic examination", 'p' ),
   Y( "Classification assessed after chemotherapy and/or radiation", 'y' ),
   R( "Classification for a recurrent tumor", 'r' ),
   A( "Classification determined at autopsy", 'a' ),
   U( "Classification determined by ultrasound or endosonography", 'u' ),
   UNSPECIFIED( "Classification determination unspecified; assume clinical examination", '-' );

   final private String _title;
   final private char _characterCode;

   TnmClassPrefixType( final String title, final char characterCode ) {
      _title = title;
      _characterCode = characterCode;
   }

   String getTitle() {
      return _title;
   }

   char getCharacterCode() {
      return _characterCode;
   }

   static TnmClassPrefixType getPrefix( final CharSequence lookupWindow, final int classStartOffset ) {
      if ( classStartOffset <= 0 ) {
         return UNSPECIFIED;
      }
      final char c = lookupWindow.charAt( classStartOffset - 1 );
      for ( TnmClassPrefixType prefix : values() ) {
         if ( c == prefix._characterCode ) {
            return prefix;
         }
      }
      return UNSPECIFIED;
   }

}
