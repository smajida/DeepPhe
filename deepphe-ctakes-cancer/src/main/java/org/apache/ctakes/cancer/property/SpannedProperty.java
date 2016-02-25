package org.apache.ctakes.cancer.property;

import org.apache.ctakes.cancer.util.SpannedEntity;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 2/8/2016
 */
public interface SpannedProperty<T extends Type, V extends Value> extends SpannedEntity {

   SpannedType<T> getSpannedType();

   SpannedValue<V> getSpannedValue();

}
