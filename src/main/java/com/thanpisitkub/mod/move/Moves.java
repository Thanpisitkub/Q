package com.thanpisitkub.mod.move;

import java.util.List;
import java.util.Locale;

/**
 * The two moves of the "thanpisitkub" power and their timelines.
 *
 * <p>Timing was laid out against the supplied animations and audio:
 * <ul>
 *   <li>Galaxy Impact: gather 0-4.4 s, then the particle events of the supplied garp_ult1 animation
 *       (fist flurry 4.5-12.7 s, big punches 12.9-14.1 s, ground-slam finale 16.6 s).</li>
 *   <li>Galaxy Divide: slashes at 1.5-3 s, travelling "divide" waves at 5-7 s,
 *       main slash at 16.5 s and the sky-splitting climax at ~22.3 s.</li>
 * </ul>
 * Everything here is plain data - tweak the numbers freely. Distances are in blocks,
 * relative to the caster ({@code forward} = direction the caster faces).
 */
public final class Moves {
    private Moves() {
    }

    public static final MoveDef GALAXY_IMPACT = galaxyImpact();
    public static final MoveDef GALAXY_DIVIDE = galaxyDivide();

    /** Index = id sent over the network. */
    public static final List<MoveDef> ALL = List.of(GALAXY_IMPACT, GALAXY_DIVIDE);

    public static MoveDef byIndex(int i) {
        return i >= 0 && i < ALL.size() ? ALL.get(i) : null;
    }

    public static MoveDef byId(String id) {
        if (id == null) return null;
        String k = id.toLowerCase(Locale.ROOT);
        for (MoveDef m : ALL) if (m.id.equals(k)) return m;
        return null;
    }

    // ------------------------------------------------------------------------------------------

    private static MoveDef galaxyImpact() {
        MoveDef.Builder b = MoveDef.builder("galaxy_impact", 0, "galaxy_impact", 27.0);

        // --- sound: only the supplied track (sounds.zip / garp_ult1.ogg) -----------------------
        b.sound(0.0, "garp_ult1", 3.0f, 1.0f);

        // --- gather power (0 - 3.4 s) ------------------------------------------------------
        b.fxEvery(0.0, 3.0, 1.0, "garp_rubi2", 0, 0, 0.1);
        b.fx(1.6, "garp_ult1_6_2", 0, 0, 0.15);

        // --- galaxy forms overhead (3.4 - 6.9 s) ---------------------------------------------
        b.fxEvery(3.4, 6.4, 1.0, "garp_ult1_1_2", 0, 0, 14);
        b.fx(3.6, "garp_ult1_3_3", 0, 0, 1.0);
        b.fx(5.2, "garp_ult1_3_3", 0, 0, 1.0);

        // --- particle timeline from the supplied garp_ult1 animation (4.5 s - 16.6 s) -------------
        // Each line: time (s), particle, forward, side, up. Fist = right hand; slash_loc9/10 follow the
        // climbing locators of the animation. garp_ult1_7 was not in the supplied files, so the big
        // punches (12.9 / 13.8 / 14.1 s) use garp_ult1_4 + garp_ult1_9 + garp_ult1_8 instead.
        b.fx(4.458, "garp_rubi2_4", 0, 0, 0.2);
        b.fx(4.542, "garp_ult1_2", 3.2, -2, 5.2);
        b.fx(4.562, "garp_ult1_12", 3.2, -2, 5.2);
        b.fx(4.562, "garp_ult1_3", 3.2, -2, 5.2);
        b.fx(4.583, "garp_skills4_1", 3.2, -2, 5.2);
        b.fx(5.5, "garp_ult1_2_2", 3.2, -2, 5.2);
        b.fx(6.042, "garp_skills4_1", 3.2, -2, 5.2);
        b.fx(6.417, "garp_ult1_2_2", 3.2, -2, 5.2);
        b.fx(7.1, "garp_rubi2_4", 0, 0, 0.2);
        b.fx(7.25, "garp_ult1_2", 3.2, -2, 5.2);
        b.fx(7.27, "garp_ult1_12", 3.2, -2, 5.2);
        b.fx(7.27, "garp_ult1_3", 3.2, -2, 5.2);
        b.fx(7.292, "garp_skills4_1", 3.2, -2, 5.2);
        b.fx(7.875, "garp_skills4_1", 3.2, -2, 5.2);
        b.fx(8.083, "garp_ult1_2", 3.2, -2, 5.2);
        b.fx(8.103, "garp_ult1_12", 3.2, -2, 5.2);
        b.fx(8.103, "garp_ult1_3", 3.2, -2, 5.2);
        b.fx(8.75, "garp_ult1_6", 4, 0, 10.19);
        b.fx(8.77, "garp_ult1_5", 4, 0, 10.19);
        b.fx(8.85, "garp_ult1_8_2", 4, 0, 10.37);
        b.fx(8.917, "garp_ult1_4_2", 3.2, -2, 5.2);
        b.fx(9, "garp_ult1_2", 3.2, -2, 5.2);
        b.fx(9.02, "garp_ult1_12", 3.2, -2, 5.2);
        b.fx(9.02, "garp_ult1_3", 3.2, -2, 5.2);
        b.fx(9.083, "garp_ult1_1", 4, 0, 14.42);
        b.fx(9.375, "garp_rubi2", 0, 0, 0.2);
        b.fx(9.958, "garp_ult1_2", 3.2, -2, 5.2);
        b.fx(9.978, "garp_ult1_12", 3.2, -2, 5.2);
        b.fx(9.978, "garp_ult1_3", 3.2, -2, 5.2);
        b.fx(10.125, "garp_ult1_6_2", 4, 0, 12.66);
        b.fx(10.145, "garp_ult1_5", 4, 0, 12.66);
        b.fx(10.25, "garp_skills4_1", 3.2, -2, 5.2);
        b.fx(10.542, "garp_ult1_1_2", 4, 0, 17.26);
        b.fx(10.875, "garp_ult1_2", 3.2, -2, 5.2);
        b.fx(10.895, "garp_ult1_12", 3.2, -2, 5.2);
        b.fx(10.895, "garp_ult1_3", 3.2, -2, 5.2);
        b.fx(11.708, "garp_skills4_1", 3.2, -2, 5.2);
        b.fx(11.75, "garp_ult1_6_2", 4, 0, 15.58);
        b.fx(11.77, "garp_ult1_5", 4, 0, 15.58);
        b.fx(11.792, "garp_ult1_2", 3.2, -2, 5.2);
        b.fx(11.812, "garp_ult1_12", 3.2, -2, 5.2);
        b.fx(11.812, "garp_ult1_3", 3.2, -2, 5.2);
        b.fx(12, "garp_ult1_1_2", 4, 0, 21.08);
        b.fx(12.417, "garp_ult1_6_2", 4, 0, 16.78);
        b.fx(12.437, "garp_ult1_5", 4, 0, 16.78);
        b.fx(12.708, "garp_ult1_2", 3.2, -2, 5.2);
        b.fx(12.728, "garp_ult1_12", 3.2, -2, 5.2);
        b.fx(12.728, "garp_ult1_3", 3.2, -2, 5.2);
        b.fx(12.875, "garp_rubi2", 0, 0, 0.2);
        b.fx(12.91, "garp_ult1_4", 3.2, -2, 5.2);
        b.fx(12.91, "garp_ult1_9", 3.2, -2, 5.2);
        b.fx(12.91, "garp_ult1_8", 3.2, -2, 5.2);
        b.fx(13.375, "garp_skills4_1", 3.2, -2, 5.2);
        b.fx(13.791, "garp_ult1_4", 3.2, -2, 5.2);
        b.fx(13.791, "garp_ult1_9", 3.2, -2, 5.2);
        b.fx(13.791, "garp_ult1_8", 3.2, -2, 5.2);
        b.fx(14.1, "garp_ult1_4", 3.2, -2, 5.2);
        b.fx(14.1, "garp_ult1_9", 3.2, -2, 5.2);
        b.fx(14.1, "garp_ult1_8", 3.2, -2, 5.2);
        b.fx(16.625, "garp_rubi2_4", 0, 0, 0.2);
        b.fx(16.625, "garp_ult1_8", 0, 0, 0.3);
        b.fx(16.625, "garp_ult1_10", 0, 0, 0.3);
        b.fx(16.625, "garp_ult1_13", 0, 0, 0.3);
        b.fx(16.625, "garp_ult1_9", 0, 0, 5);
        b.fx(16.625, "garp_ult1_11", 0, 0, 0.3);
        b.fx(16.625, "garp_ult1_4", 0, 0, 1);
        b.fx(16.625, "garp_ult1_5", 0, 0, 0.3);
        b.fx(17.125, "garp_ult1_8", 0, 0, 0.3);
        b.fx(17.125, "garp_ult1_10", 0, 0, 0.3);
        b.fx(17.625, "garp_ult1_13", 0, 0, 0.3);

        // --- damage: fist flurry, three big punches, ground-slam finale (16.6 s) -----------------
        b.lineHit(4.542, 6, 4, 2f, 0.5, 0.1);
        b.lineHit(7.25, 6, 4, 2f, 0.5, 0.1);
        b.lineHit(8.083, 6, 4, 2f, 0.5, 0.1);
        b.lineHit(9, 6, 4, 2f, 0.5, 0.1);
        b.lineHit(9.958, 6, 4, 2f, 0.5, 0.1);
        b.lineHit(10.875, 6, 4, 2f, 0.5, 0.1);
        b.lineHit(11.792, 6, 4, 2f, 0.5, 0.1);
        b.lineHit(12.708, 6, 4, 2f, 0.5, 0.1);
        b.sphereHit(12.91, 8, 6f, 1, 0.4);
        b.sphereHit(13.791, 8, 6f, 1, 0.4);
        b.sphereHit(14.1, 8, 6f, 1, 0.4);
        b.sphereHit(16.625, 16, 40f, 2, 0.8);

        return b.build();
    }

    // ------------------------------------------------------------------------------------------

    private static MoveDef galaxyDivide() {
        MoveDef.Builder b = MoveDef.builder("galaxy_divide", 1, "galaxy_divide", 27.5);

        // --- sound -------------------------------------------------------------------------
        b.sound(0.0, "garp_galaxydivide", 3.0f, 1.0f);

        // --- opening: flame burst + rapid slashes (0 - 3 s) -----------------------------------
        b.fx(0.0, "garp_ult4_5_2", 0, 0, 0.3);
        b.fx(0.5, "garp_rubi2", 0, 0, 0.1);
        slash(b, 1.5, 3.0, 1.2);
        slash(b, 2.0, 3.2, -1.2);
        slash(b, 2.5, 3.4, 1.2);
        slash(b, 3.0, 3.6, -1.2);
        b.lineHit(1.5, 8, 5, 5f, 0.6, 0.2);
        b.lineHit(2.5, 9, 5, 6f, 0.8, 0.2);

        // --- travelling divide waves (5 - 7.5 s) ----------------------------------------------
        b.fxLine(5.2, 0.3, "garp_galaxydivide2", 3, 18, 3, 0, 0.2);
        b.fx(5.5, "garp_ult4_2", 3, 0, 0.5);
        b.fx(6.5, "garp_ult4_2", 6, 0, 0.5);
        b.fx(6.4, "garp_galaxydivide1", 8, 0, 1.4);
        b.lineHit(6.5, 18, 5, 8f, 1.0, 0.3);

        // --- gather (13 - 16.4 s) ---------------------------------------------------------------
        b.fxEvery(13.0, 16.0, 1.0, "garp_ult4_5_2", 0, 0, 0.3);
        b.fxEvery(13.5, 15.5, 1.0, "garp_rubi2", 0, 0, 0.1);
        b.fx(13.5, "garp_ult4_2", 0, 0, 0.5);
        b.fx(15.0, "garp_ult4_2", 0, 0, 0.5);
        b.fx(14.5, "garp_galaxydivide2", 0, 0, 0.2);
        b.fx(15.5, "garp_galaxydivide2", 0, 0, 0.2);

        // --- main divide (16.5 s): a wave cuts 32 blocks straight ahead ------------------------------
        b.fx(16.5, "garp_galaxydivide1", 3, 0, 1.4);
        b.fx(16.7, "garp_galaxydivide1", 9, 0, 1.4);
        b.fx(16.9, "garp_galaxydivide1", 15, 0, 1.4);
        b.fx(17.1, "garp_galaxydivide1", 21, 0, 1.4);
        b.fx(17.3, "garp_galaxydivide1", 27, 0, 1.4);
        b.fxLine(16.5, 0.1, "garp_galaxydivide2", 2, 32, 3, 0, 0.2);
        b.fxLine(16.5, 0.1, "garp_skills4_1", 5, 30, 5, 0, 1.2);
        b.fx(16.6, "garp_ult4_5", 4, 0, 0.3);
        b.fx(16.9, "garp_ult4_5", 14, 0, 0.3);
        b.fx(17.2, "garp_ult4_5", 24, 0, 0.3);
        b.lineHit(16.8, 32, 5, 25f, 1.5, 0.5);

        // --- climax (22.3 s): light pillars along the cut ---------------------------------------------
        b.fx(22.3, "garp_ult4_2_3", 4, 0, 22);
        b.fx(22.4, "garp_ult4_2_3", 10, 0, 22);
        b.fx(22.5, "garp_ult4_2_3", 16, 0, 22);
        b.fx(22.6, "garp_ult4_2_3", 22, 0, 22);
        b.fx(22.7, "garp_ult4_2_3", 28, 0, 22);
        b.fxLine(22.3, 0.2, "garp_galaxydivide1", 6, 30, 6, 0, 1.4);
        b.fxLine(22.3, 0.08, "garp_galaxydivide2", 0, 32, 2, 0, 0.2);
        b.fxLine(22.3, 0.2, "garp_skills4_1", 4, 28, 6, 0, 1.2);
        b.fx(22.4, "garp_ult4_5_2", 8, 0, 0.5);
        b.fx(22.7, "garp_ult4_5_2", 16, 0, 0.5);
        b.fx(23.0, "garp_ult4_5_2", 24, 0, 0.5);
        b.lineHit(22.4, 36, 7, 45f, 2.0, 0.8);

        return b.build();
    }

    private static void slash(MoveDef.Builder b, double t, double forward, double side) {
        b.fx(t, "garp_galaxydivide1", forward, side, 1.4);
        b.fx(t, "garp_skills4_1", forward, side, 1.3);
        b.fx(t + 0.1, "garp_ult4_2", forward, side, 0.8);
    }
}
