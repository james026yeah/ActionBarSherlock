/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.ResolveInfo;
import android.os.Build;

/**
 * Helper for accessing newer features in AccessibilityServiceInfo.
 */
public class AccessibilityServiceInfoCompat {

    static interface AccessibilityServiceInfoVersionImpl {
        public String getId(AccessibilityServiceInfo info);
        public ResolveInfo getResolveInfo(AccessibilityServiceInfo info);
        public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo info);
        public String getDescription(AccessibilityServiceInfo info);
        public String getSettingsActivityName(AccessibilityServiceInfo info);
    }

    static class AccessibilityServiceInfoStubImpl implements AccessibilityServiceInfoVersionImpl {

        public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo info) {
            return false;
        }

        public String getDescription(AccessibilityServiceInfo info) {
            return null;
        }

        public String getId(AccessibilityServiceInfo info) {
            return null;
        }

        public ResolveInfo getResolveInfo(AccessibilityServiceInfo info) {
            return null;
        }

        public String getSettingsActivityName(AccessibilityServiceInfo info) {
            return null;
        }
    }

    static class AccessibilityServiceInfoIcsImpl extends AccessibilityServiceInfoStubImpl {

        @Override
        public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo info) {
            return AccessibilityServiceInfoCompatIcs.getCanRetrieveWindowContent(info);
        }

        @Override
        public String getDescription(AccessibilityServiceInfo info) {
            return AccessibilityServiceInfoCompatIcs.getDescription(info);
        }

        @Override
        public String getId(AccessibilityServiceInfo info) {
            return AccessibilityServiceInfoCompatIcs.getId(info);
        }

        @Override
        public ResolveInfo getResolveInfo(AccessibilityServiceInfo info) {
            return AccessibilityServiceInfoCompatIcs.getResolveInfo(info);
        }

        @Override
        public String getSettingsActivityName(AccessibilityServiceInfo info) {
            return AccessibilityServiceInfoCompatIcs.getSettingsActivityName(info);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 14) { // ICS
            IMPL = new AccessibilityServiceInfoIcsImpl();
        } else {
            IMPL = new AccessibilityServiceInfoStubImpl();
        }
    }

    private static final AccessibilityServiceInfoVersionImpl IMPL;

    /**
     * Mask for all feedback types.
     *
     * @see AccessibilityServiceInfo#FEEDBACK_SPOKEN
     * @see AccessibilityServiceInfo#FEEDBACK_HAPTIC
     * @see AccessibilityServiceInfo#FEEDBACK_AUDIBLE
     * @see AccessibilityServiceInfo#FEEDBACK_VISUAL
     * @see AccessibilityServiceInfo#FEEDBACK_GENERIC
     */
    public static final int FEEDBACK_ALL_MASK = 0xFFFFFFFF;

    /*
     * Hide constructor
     */
    private AccessibilityServiceInfoCompat() {

    }

    /**
     * The accessibility service id.
     * <p>
     * <strong>Generated by the system.</strong>
     * </p>
     *
     * @return The id.
     */
    public static String getId(AccessibilityServiceInfo info) {
        return IMPL.getId(info);
    }

    /**
     * The service {@link ResolveInfo}.
     * <p>
     * <strong>Generated by the system.</strong>
     * </p>
     *
     * @return The info.
     */
    public static ResolveInfo getResolveInfo(AccessibilityServiceInfo info) {
        return IMPL.getResolveInfo(info);
    }

    /**
     * The settings activity name.
     * <p>
     * <strong>Statically set from {@link AccessibilityService#SERVICE_META_DATA
     * meta-data}.</strong>
     * </p>
     *
     * @return The settings activity name.
     */
    public static String getSettingsActivityName(AccessibilityServiceInfo info) {
        return IMPL.getSettingsActivityName(info);
    }

    /**
     * Whether this service can retrieve the current window's content.
     * <p>
     * <strong>Statically set from {@link AccessibilityService#SERVICE_META_DATA
     * meta-data}.</strong>
     * </p>
     *
     * @return True window content can be retrieved.
     */
    public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo info) {
        return IMPL.getCanRetrieveWindowContent(info);
    }

    /**
     * Description of the accessibility service.
     * <p>
     * <strong>Statically set from {@link AccessibilityService#SERVICE_META_DATA
     * meta-data}.</strong>
     * </p>
     *
     * @return The description.
     */
    public static String getDescription(AccessibilityServiceInfo info) {
        return IMPL.getDescription(info);
    }

    /**
     * Returns the string representation of a feedback type. For example,
     * {@link AccessibilityServiceInfo#FEEDBACK_SPOKEN} is represented by the
     * string FEEDBACK_SPOKEN.
     *
     * @param feedbackType The feedback type.
     * @return The string representation.
     */
    public static String feedbackTypeToString(int feedbackType) {
        switch (feedbackType) {
            case AccessibilityServiceInfo.FEEDBACK_AUDIBLE:
                return "FEEDBACK_AUDIBLE";
            case AccessibilityServiceInfo.FEEDBACK_HAPTIC:
                return "FEEDBACK_HAPTIC";
            case AccessibilityServiceInfo.FEEDBACK_GENERIC:
                return "FEEDBACK_GENERIC";
            case AccessibilityServiceInfo.FEEDBACK_SPOKEN:
                return "FEEDBACK_SPOKEN";
            case AccessibilityServiceInfo.FEEDBACK_VISUAL:
                return "FEEDBACK_VISUAL";
            case AccessibilityServiceInfoCompat.FEEDBACK_ALL_MASK:
                return "FEEDBACK_ALL";
            default:
                return null;
        }
    }

    /**
     * Returns the string representation of a flag. For example,
     * {@link AccessibilityServiceInfo#DEFAULT} is represented by the
     * string DEFAULT.
     *
     * @param flag The flag.
     * @return The string representation.
     */
    public static String flagToString(int flag) {
        switch (flag) {
            case AccessibilityServiceInfo.DEFAULT:
                return "DEFAULT";
            default:
                return null;
        }
    }
}
