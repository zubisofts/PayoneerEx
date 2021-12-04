package com.zubisofts.payoneerex.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class State<T> {

   @NonNull
   public final Status status;

   @Nullable
   public final T data;

   public State(@NonNull Status status, @Nullable T data) {
      this.status = status;
      this.data = data;
   }

   public static <T> State<T> success(@Nullable T data) {
      return new State<>(Status.SUCCESS, data);
   }

   public static <T> State<T> error(@Nullable T data) {
      return ErrorStateUtil.decodeErrorState(data);
   }

   public static <T> State<T> loading() {
      return new State<>(Status.LOADING, null);
   }
}
