<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="gradroleApp.userextra.home.createOrEditLabel"
          data-cy="UserextraCreateUpdateHeading"
          v-text="$t('gradroleApp.userextra.home.createOrEditLabel')"
        >
          Create or edit a Userextra
        </h2>
        <div>
          <div class="form-group" v-if="userextra.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="userextra.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.userextra.middlename')" for="userextra-middlename">Middlename</label>
            <input
              type="text"
              class="form-control"
              name="middlename"
              id="userextra-middlename"
              data-cy="middlename"
              :class="{ valid: !$v.userextra.middlename.$invalid, invalid: $v.userextra.middlename.$invalid }"
              v-model="$v.userextra.middlename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.userextra.jobrole')" for="userextra-jobrole">Jobrole</label>
            <input
              type="text"
              class="form-control"
              name="jobrole"
              id="userextra-jobrole"
              data-cy="jobrole"
              :class="{ valid: !$v.userextra.jobrole.$invalid, invalid: $v.userextra.jobrole.$invalid }"
              v-model="$v.userextra.jobrole.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.userextra.dob')" for="userextra-dob">Dob</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="userextra-dob"
                  v-model="$v.userextra.dob.$model"
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
                id="userextra-dob"
                data-cy="dob"
                type="text"
                class="form-control"
                name="dob"
                :class="{ valid: !$v.userextra.dob.$invalid, invalid: $v.userextra.dob.$invalid }"
                v-model="$v.userextra.dob.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.userextra.user')" for="userextra-user">User</label>
            <select class="form-control" id="userextra-user" data-cy="user" name="user" v-model="userextra.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="userextra.user && userOption.id === userextra.user.id ? userextra.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.login }}
              </option>
            </select>
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
            :disabled="$v.userextra.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./userextra-update.component.ts"></script>
