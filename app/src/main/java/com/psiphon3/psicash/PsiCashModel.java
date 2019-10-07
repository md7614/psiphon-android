/*
 *
 * Copyright (c) 2019, Psiphon Inc.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.psiphon3.psicash;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.List;

import ca.psiphon.psicashlib.PsiCashLib;

public interface PsiCashModel {
    @AutoValue
    abstract class PsiCash implements PsiCashModel {
        static Builder builder() {
            return new AutoValue_PsiCashModel_PsiCash.Builder();
        }

        public abstract long balance();

        public abstract long reward();

        public abstract String diagnosticInfo();

        @Nullable
        public abstract List<PsiCashLib.PurchasePrice> purchasePrices();

        @Nullable
        public abstract PsiCashLib.Purchase nextExpiringPurchase();

        @AutoValue.Builder
        abstract static class Builder {
            abstract Builder balance(long balance);

            abstract Builder reward(long reward);

            abstract Builder diagnosticInfo(String info);

            abstract Builder purchasePrices(@Nullable List<PsiCashLib.PurchasePrice> purchasePrices);

            abstract Builder nextExpiringPurchase(@Nullable PsiCashLib.Purchase purchase);

            abstract PsiCash build();
        }
    }

    @AutoValue
    abstract class ExpiringPurchase implements PsiCashModel {
        public abstract PsiCashLib.Purchase expiringPurchase();

        public static ExpiringPurchase create(PsiCashLib.Purchase purchase) {
            return new AutoValue_PsiCashModel_ExpiringPurchase(purchase);
        }
    }

    @AutoValue
    abstract class RewardedVideoState implements PsiCashModel {
        enum VideoState {LOADED, PLAYING}

        abstract VideoState videoState();

        public static RewardedVideoState playing() {
            return new AutoValue_PsiCashModel_RewardedVideoState(VideoState.PLAYING);
        }

        public static RewardedVideoState loaded() {
            return new AutoValue_PsiCashModel_RewardedVideoState(VideoState.LOADED);
        }
    }

    @AutoValue
    abstract class Reward implements PsiCashModel {
        public abstract long amount();

        public static Reward create(long amount) {
            return new AutoValue_PsiCashModel_Reward(amount);
        }
    }

}