/*
 * Copyright (c) 2023 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.element.android.features.lockscreen.impl.create

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.element.android.features.lockscreen.impl.create.model.PinEntry
import io.element.android.features.lockscreen.impl.create.validation.CreatePinFailure

open class CreatePinStateProvider : PreviewParameterProvider<CreatePinState> {
    override val values: Sequence<CreatePinState>
        get() = sequenceOf(
            aCreatePinState(),
            aCreatePinState(
                choosePinEntry = PinEntry.empty(4).fillWith("12")
            ),
            aCreatePinState(
                choosePinEntry = PinEntry.empty(4).fillWith("1789"),
                isConfirmationStep = true,
            ),
            aCreatePinState(
                choosePinEntry = PinEntry.empty(4).fillWith("1789"),
                confirmPinEntry = PinEntry.empty(4).fillWith("1788"),
                isConfirmationStep = true,
                creationFailure = CreatePinFailure.PinsDontMatch
            ),
            aCreatePinState(
                choosePinEntry = PinEntry.empty(4).fillWith("1111"),
                creationFailure = CreatePinFailure.PinBlacklisted
            ),

        )
}

fun aCreatePinState(
    choosePinEntry: PinEntry = PinEntry.empty(4),
    confirmPinEntry: PinEntry = PinEntry.empty(4),
    isConfirmationStep: Boolean = false,
    creationFailure: CreatePinFailure? = null,
) = CreatePinState(
    choosePinEntry = choosePinEntry,
    confirmPinEntry = confirmPinEntry,
    isConfirmationStep = isConfirmationStep,
    createPinFailure = creationFailure,
    eventSink = {}
)

