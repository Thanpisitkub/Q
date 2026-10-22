package com.thanpisitkub.mod.move;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Describes one move (e.g. Galaxy Impact): which animation to play, when to spawn
 * which particle emitter, when to play which sound and when to hurt things.
 * Times are stored in game ticks (20 per second). Pure Java (no Minecraft classes).
 */
public final class MoveDef {

    /** A particle emitter spawned relative to the caster (forward = where the caster faces). */
    public record Fx(int tick, String particle, double forward, double side, double up) {
    }

    /** A sound that follows the caster. {@code volume} above 1.0 only widens the audible range. */
    public record Snd(int tick, String sound, float volume, float pitch) {
    }

    public enum HitShape {SPHERE, LINE}

    /**
     * Damage event. SPHERE: {@code size} = radius around the caster.
     * LINE: {@code size} = length in front of the caster and {@code width} = full width.
     */
    public record Hit(int tick, HitShape shape, double size, double width, float damage, double knockback, double knockUp) {
    }

    public final String id;
    public final int index;
    public final String animation;
    public final int totalTicks;
    public final List<Fx> fx;
    public final List<Snd> sounds;
    public final List<Hit> hits;

    private MoveDef(Builder b) {
        this.id = b.id;
        this.index = b.index;
        this.animation = b.animation;
        this.totalTicks = b.totalTicks;
        b.fx.sort(Comparator.comparingInt(Fx::tick));
        b.sounds.sort(Comparator.comparingInt(Snd::tick));
        b.hits.sort(Comparator.comparingInt(Hit::tick));
        this.fx = List.copyOf(b.fx);
        this.sounds = List.copyOf(b.sounds);
        this.hits = List.copyOf(b.hits);
    }

    public static Builder builder(String id, int index, String animation, double totalSeconds) {
        return new Builder(id, index, animation, totalSeconds);
    }

    public static int ticks(double seconds) {
        return (int) Math.round(seconds * 20.0);
    }

    public static final class Builder {
        private final String id;
        private final int index;
        private final String animation;
        private final int totalTicks;
        private final List<Fx> fx = new ArrayList<>();
        private final List<Snd> sounds = new ArrayList<>();
        private final List<Hit> hits = new ArrayList<>();

        private Builder(String id, int index, String animation, double totalSeconds) {
            this.id = id;
            this.index = index;
            this.animation = animation;
            this.totalTicks = ticks(totalSeconds);
        }

        /** One emitter at a moment in time. */
        public Builder fx(double sec, String particle, double forward, double side, double up) {
            fx.add(new Fx(ticks(sec), particle, forward, side, up));
            return this;
        }

        /** The same emitter repeated every {@code stepSec} from {@code startSec} to {@code endSec} (inclusive). */
        public Builder fxEvery(double startSec, double endSec, double stepSec, String particle, double forward, double side, double up) {
            for (double t = startSec; t <= endSec + 1e-9; t += stepSec) fx(t, particle, forward, side, up);
            return this;
        }

        /**
         * A wave travelling away from the caster: the first emitter at {@code fromForward}
         * at {@code startSec}, the next {@code stepForward} blocks further {@code stepSec} later, and so on.
         */
        public Builder fxLine(double startSec, double stepSec, String particle, double fromForward, double toForward, double stepForward, double side, double up) {
            double t = startSec;
            for (double d = fromForward; d <= toForward + 1e-9; d += stepForward) {
                fx(t, particle, d, side, up);
                t += stepSec;
            }
            return this;
        }

        public Builder sound(double sec, String sound, float volume, float pitch) {
            sounds.add(new Snd(ticks(sec), sound, volume, pitch));
            return this;
        }

        public Builder sphereHit(double sec, double radius, float damage, double knockback, double knockUp) {
            hits.add(new Hit(ticks(sec), HitShape.SPHERE, radius, radius * 2, damage, knockback, knockUp));
            return this;
        }

        public Builder lineHit(double sec, double length, double width, float damage, double knockback, double knockUp) {
            hits.add(new Hit(ticks(sec), HitShape.LINE, length, width, damage, knockback, knockUp));
            return this;
        }

        public MoveDef build() {
            return new MoveDef(this);
        }
    }
}
