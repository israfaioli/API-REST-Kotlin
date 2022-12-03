package model

import lombok.Builder
import lombok.Data

    @Data
    @Builder(toBuilder = true)
    public class Activity {

        private val id: Int? = null
        private val title: String? = null
        private val dueDate: String? = null
        private val completed: Boolean? = null
}