<template>
  <div>
    <h2 id="page-heading" data-cy="UserextraHeading">
      <span v-text="$t('gradroleApp.userextra.home.title')" id="userextra-heading">Userextras</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('gradroleApp.userextra.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link
          :to="{ name: 'UserextraCreate' }"
          tag="button"
          id="jh-create-entity"
          data-cy="entityCreateButton"
          class="btn btn-primary jh-create-entity create-userextra"
        >
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('gradroleApp.userextra.home.createLabel')"> Create a new Userextra </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && userextras && userextras.length === 0">
      <span v-text="$t('gradroleApp.userextra.home.notFound')">No userextras found</span>
    </div>
    <div class="table-responsive" v-if="userextras && userextras.length > 0">
      <table class="table table-striped" aria-describedby="userextras">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('gradroleApp.userextra.middlename')">Middlename</span></th>
            <th scope="row"><span v-text="$t('gradroleApp.userextra.jobrole')">Jobrole</span></th>
            <th scope="row"><span v-text="$t('gradroleApp.userextra.dob')">Dob</span></th>
            <th scope="row"><span v-text="$t('gradroleApp.userextra.user')">User</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="userextra in userextras" :key="userextra.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'UserextraView', params: { userextraId: userextra.id } }">{{ userextra.id }}</router-link>
            </td>
            <td>{{ userextra.middlename }}</td>
            <td>{{ userextra.jobrole }}</td>
            <td>{{ userextra.dob }}</td>
            <td>
              {{ userextra.user ? userextra.user.login : '' }}
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'UserextraView', params: { userextraId: userextra.id } }"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
                <router-link
                  :to="{ name: 'UserextraEdit', params: { userextraId: userextra.id } }"
                  tag="button"
                  class="btn btn-primary btn-sm edit"
                  data-cy="entityEditButton"
                >
                  <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(userextra)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="gradroleApp.userextra.delete.question" data-cy="userextraDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-userextra-heading" v-text="$t('gradroleApp.userextra.delete.question', { id: removeId })">
          Are you sure you want to delete this Userextra?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-userextra"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeUserextra()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./userextra.component.ts"></script>
