package com.morecreepsrevival.morecreeps.common.entity;

public interface IEntityCanChangeSize {
    /**
     * The maximun size this entity can be.
     */
    float maxGrowth ();
    /**
     * The amount a grow ray grows this entity when it hits.
     */
    float getGrowRayAmount ();
    /**
     * What happens when this entity grows.
     */
    void onGrow (EntityGrow source);
}
