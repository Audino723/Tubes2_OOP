package com.aetherwars.util;

public enum Status {
    PERM, TEMP, INACTIVE, FIRSTROUND, SECONDROUND
};

// Descriptions of Status:
// PERM: Permanent status
// TEMP: Temporary status
// INACTIVE: Inactive (equal to deativated because of the duration)
// FIRSTROUND: On first round since activated, duration not deducted yet
// SECONDROUND: On second round since activated, duration deducted, status changed to PERM OR TEMP