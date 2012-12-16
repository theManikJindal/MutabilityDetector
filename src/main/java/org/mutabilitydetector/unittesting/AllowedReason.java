/*
 *    Copyright (c) 2008-2011 Graham Allan
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.mutabilitydetector.unittesting;

import static com.google.common.collect.Iterables.transform;
import static java.util.Arrays.asList;
import static org.mutabilitydetector.locations.Dotted.CLASS_TO_DOTTED;
import static org.mutabilitydetector.locations.Dotted.dotted;
import static org.mutabilitydetector.locations.Dotted.fromClass;

import org.hamcrest.Matcher;
import org.mutabilitydetector.MutableReasonDetail;
import org.mutabilitydetector.unittesting.matchers.reasons.AllowingForSubclassing;
import org.mutabilitydetector.unittesting.matchers.reasons.AllowingNonFinalFields;
import org.mutabilitydetector.unittesting.matchers.reasons.AssumingTheFields;
import org.mutabilitydetector.unittesting.matchers.reasons.NoReasonsAllowed;
import org.mutabilitydetector.unittesting.matchers.reasons.ProvidedOtherClass;

/**
 * Provides ways to suppress false positives generated by Mutability Detector.
 * <p>
 * Regretfully, Mutability Detector may produce false positives, which cause
 * your unit tests to fail, even though your class is immutable. In order to get
 * around this fault in Mutability Detector, you can provide an "allowed reason"
 * for mutability. This is preferable to deleting the test, or marking it as
 * ignored, as it allows checking for all other violations except those you have
 * explicitly sanctioned.
 * <p>
 * All types returned from the static methods on AllowedReason are
 * implementations of Hamcrest {@link Matcher}, generic on the type
 * {@link MutableReasonDetail}. 
 * <p>
 * For more information on configuring a mutability assertion, see {@link MutabilityAssert}.
 * 
 * @see MutabilityAssert
 * @see MutableReasonDetail
 * 
 * @see ProvidedOtherClass
 * @see AllowingForSubclassing
 * @see AllowingNonFinalFields
 * @see AssumingTheFields
 * @see NoReasonsAllowed
 * 
 */
public final class AllowedReason {

    private AllowedReason() { }

    public static ProvidedOtherClass provided(String dottedClassName) {
        return ProvidedOtherClass.provided(dotted(dottedClassName));
    }

    public static ProvidedOtherClass provided(Class<?> clazz) {
        return ProvidedOtherClass.provided(fromClass(clazz));
    }

    public static ProvidedOtherClass provided(Class<?>... classes) {
        return ProvidedOtherClass.provided(transform(asList(classes), CLASS_TO_DOTTED));
    }

    public static AllowingForSubclassing allowingForSubclassing() {
        return new AllowingForSubclassing();
    }

    public static AllowingNonFinalFields allowingNonFinalFields() {
        return new AllowingNonFinalFields();
    }
    
    public static AssumingTheFields assumingTheFieldsNamed(String first, String... rest) {
        return AssumingTheFields.named(first, rest);
    }

    public static NoReasonsAllowed noReasonsAllowed() {
        return NoReasonsAllowed.noReasonsAllowed();
    }


}
