<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="gradroleApp.student.home.createOrEditLabel"
          data-cy="StudentCreateUpdateHeading"
          v-text="$t('gradroleApp.student.home.createOrEditLabel')"
        >
          Create or edit a Student
        </h2>
        <div>
          <div class="form-group" v-if="student.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="student.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.student.name')" for="student-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="student-name"
              data-cy="name"
              :class="{ valid: !$v.student.name.$invalid, invalid: $v.student.name.$invalid }"
              v-model="$v.student.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.student.email')" for="student-email">Email</label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="student-email"
              data-cy="email"
              :class="{ valid: !$v.student.email.$invalid, invalid: $v.student.email.$invalid }"
              v-model="$v.student.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.student.dob')" for="student-dob">Dob</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="student-dob"
                  v-model="$v.student.dob.$model"
                  name="dob"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="student-dob"
                data-cy="dob"
                type="text"
                class="form-control"
                name="dob"
                :class="{ valid: !$v.student.dob.$invalid, invalid: $v.student.dob.$invalid }"
                v-model="$v.student.dob.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.student.course')" for="student-course">Course</label>
            <input
              type="text"
              class="form-control"
              name="course"
              id="student-course"
              data-cy="course"
              :class="{ valid: !$v.student.course.$invalid, invalid: $v.student.course.$invalid }"
              v-model="$v.student.course.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.student.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./student-update.component.ts"></script>
